import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import lastprofstanding.engine.*
import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.lecturing.*
import java.io.File
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.lecturing.Hofmann
import lastprofstanding.engine.grid.lecturing.Kruse
import lastprofstanding.engine.grid.lecturing.Stroetmann

@Composable
fun App() {
    val engine by remember { mutableStateOf(Engine.getInstance()) }
    var engineState by remember { mutableStateOf(EngineState(Grid(0, 0), Grid(0, 0), StatsCounter())) }
    var didResetGrid by remember { mutableStateOf(false) }
    var editMode by remember { mutableStateOf(false) }
    var paused by remember { mutableStateOf(true) }
    val scale by remember { mutableIntStateOf(4) }
    val editSelect: Lecturer

    if (!didResetGrid) {
        engine.load(LevelType.BASIC)
        engineState = engine.state
        didResetGrid = true
    }

    val lecturers = listOf(
        Pair(Kruse(), KruseMinion()),
        Pair(Stroetmann(), StroetmannMinion()),
        Pair(Hofmann(), HofmannMinion())
    )

    Row {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .background(Color.Green)
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
            ) {
                if (editMode) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .wrapContentWidth()
                            .background(Color.Magenta)
                    ) {
                        items(lecturers) {
                            var buttonSelect by remember { mutableStateOf(false) }
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (buttonSelect) {
                                        Color.Magenta
                                    } else {
                                        Color.Cyan
                                    }
                                ),
                                modifier = Modifier
                                    .width(250.dp),
                                onClick = {

                                    buttonSelect = !buttonSelect
                                }
                            ) {
                                Row {
                                    Image(
                                        painter = BitmapPainter(
                                            loadImageBitmap(
                                                it.first.getFile()!!.inputStream()
                                            )
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Image(
                                        painter = BitmapPainter(
                                            loadImageBitmap(
                                                it.second.getFile()!!.inputStream()
                                            )
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Row(
                                    modifier = Modifier.padding(start = 10.dp)
                                ) {
                                    Text(it.first.name, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .wrapContentWidth()
                    .background(Color.Cyan)
                    .height(40.dp)
                    .weight(1f, false)
            ) {
                Button(
                    onClick = {
                        if (paused) {
                            engine.startSimulation(SimulationSpeed.X1) { state ->
                                engineState = state
                            }
                            paused = !paused
                        } else {
                            engine.stopSimulation()
                            engineState = engine.state
                            paused = !paused
                        }
                    }
                ) {
                    if (paused) {
                        Icon(
                            painter = BitmapPainter(
                                loadImageBitmap(
                                    File(
                                        "src/desktopMain/kotlin/lastprofstanding/res/icons/play.png"
                                    ).inputStream()
                                )
                            ),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            painter = BitmapPainter(
                                loadImageBitmap(
                                    File(
                                        "src/desktopMain/kotlin/lastprofstanding/res/icons/pause.png"
                                    ).inputStream()
                                )
                            ),
                            contentDescription = null,
                        )
                    }
                }
                Button(
                    onClick = {
                        engine.load(LevelType.BASIC)
                        engineState = engine.state
                    },
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        painter = BitmapPainter(
                            loadImageBitmap(
                                File(
                                    "src/desktopMain/kotlin/lastprofstanding/res/icons/restart.png"
                                ).inputStream()
                            )
                        ),
                        contentDescription = null,
                    )
                }
                Button(
                    onClick = {
                        editMode = !editMode
                    },
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Icon(
                        painter = BitmapPainter(
                            loadImageBitmap(
                                File(
                                    "src/desktopMain/kotlin/lastprofstanding/res/icons/build.png"
                                ).inputStream()
                            )
                        ),
                        contentDescription = null,
                    )
                }
            }
        }

        val verticalScrollState = rememberScrollState()
        val horizontalScrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .verticalScroll(verticalScrollState)
                .horizontalScroll(horizontalScrollState)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            horizontalScrollState.scrollBy(-delta)
                        }
                    },
                )
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            verticalScrollState.scrollBy(-delta)
                        }
                    },
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO: Remove
            //engineState.dataGrid.apply {
            //    replace(GridPosition(2, 11), Hofmann())
            //    replace(GridPosition(9, 2), Stroetmann())
            //    replace(GridPosition(5, 7), Kruse())
            //}

            //TODO: Scaling :/
            /*
            when (input) {
                "+" -> when (scale) {
                    1 -> scale = 2
                    2 -> scale = 4
                }
                "-" -> when (scale) {
                    4 -> scale = 2
                    2 -> scale = 1
                }
            }
             */

            //engineState.tileGrid.get(GridPosition(0, 0))?.passable

            engineState.tileGrid.draw(-1f, 0, 16 * scale, false)
            engineState.dataGrid.draw(
                1f,
                -engineState.tileGrid.rowCount * 16 * scale,
                16 * scale,
                editMode
            )
            if (editMode) {

            }
        }
        Column {
            Text(engineState.dataGrid.getTextRepresentation())
        }
    }
}

