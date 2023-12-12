import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import lastprofstanding.engine.*
import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.lecturing.*
import java.io.File

@Composable
fun App() {
    val engine by remember { mutableStateOf(Engine.getInstance()) }
    var engineState by remember { mutableStateOf(EngineState(Grid(0, 0), Grid(0, 0), StatsCounter())) }
    var didResetGrid by remember { mutableStateOf(false) }
    var editMode by remember { mutableStateOf(false) }
    var paused by remember { mutableStateOf(true) }
    val scale by remember { mutableIntStateOf(8) }
    var spedUp by remember { mutableStateOf(false) }
    var buttonSelect by remember { mutableStateOf(false) }
    var editSelect: Lecturer? = null
    var abilitySelect: AbilityType? = null

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

    val abilities = listOf(
        AbilityType.SPEED_UP,
        AbilityType.SPEED_DOWN,
        AbilityType.SPAWN_RATE_UP,
        AbilityType.SPAWN_RATE_DOWN,
        AbilityType.MINION_SPEED_UP,
        AbilityType.MINION_SPEED_DOWN
    )

    Row {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .width(325.dp)
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (paused && !editMode) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .wrapContentWidth()
                    ) {
                        item {
                            Text("Apply an Ability", style = TextStyle(fontWeight = FontWeight.Bold))
                        }
                        items(abilities) {
                            OutlinedButton(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (buttonSelect || !paused) {
                                        Color.DarkGray
                                    } else {
                                        Color.LightGray
                                    }
                                ),
                                modifier = Modifier
                                    .width(250.dp),
                                onClick = {
                                    buttonSelect = !buttonSelect
                                    abilitySelect = if (abilitySelect == null) {
                                        it
                                    } else {
                                        null
                                    }
                                },
                                enabled = (abilitySelect == null || abilitySelect == it) && paused
                            ) {
                                Row(
                                    modifier = Modifier.padding(start = 10.dp)
                                ) {
                                    Text(it.name, textAlign = TextAlign.Center)
                                }
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }

                if (editMode) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .wrapContentWidth()
                    ) {
                        item {
                            Text("Select a Lecturer", style = TextStyle(fontWeight = FontWeight.Bold))
                        }
                        items(lecturers) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (buttonSelect) {
                                        Color.DarkGray
                                    } else {
                                        Color.LightGray
                                    }
                                ),
                                modifier = Modifier
                                    .width(250.dp),
                                onClick = {
                                    buttonSelect = !buttonSelect
                                    editSelect = if (editSelect == null) {
                                        it.first
                                    } else {
                                        null
                                    }
                                },
                                enabled = editSelect == null || editSelect == it.first
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
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .wrapContentWidth()
                    .height(40.dp)
                    .weight(1f, false)
            ) {
                val buttonModifier = Modifier
                    .padding(start = 10.dp)
                    .width(40.dp)

                Button(
                    onClick = {
                        if (paused) {
                            engine.startSimulation(SimulationSpeed.X1) { state ->
                                engineState = state
                            }
                            paused = !paused
                            spedUp = false
                        } else {
                            engine.stopSimulation()
                            engineState = engine.state
                            paused = !paused
                            spedUp = false
                        }
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { if (paused) putIcon("play") else putIcon("pause") }

                if (!paused) {
                    Button(
                        onClick = {
                            if (!spedUp) {
                                engine.stopSimulation()
                                engine.startSimulation(SimulationSpeed.X2) { state ->
                                    engineState = state
                                }
                                spedUp = !spedUp
                                paused = false
                            } else {
                                engine.stopSimulation()
                                engineState = engine.state
                                spedUp = !spedUp
                                paused = false
                            }
                        },
                        contentPadding = PaddingValues(0.dp),
                        modifier = buttonModifier
                    ) {
                        if (spedUp) putIcon("play") else putIcon("fastforward")
                    }
                }

                Button(
                    onClick = {
                        engine.load(LevelType.BASIC)
                        engine.stopSimulation()
                        engineState = engine.state
                        spedUp = false
                        paused = true
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("restart") }

                Button(
                    onClick = {
                        //TODO Invoke End
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("stop") }

                Button(
                    onClick = {
                        engine.stopSimulation()
                        engineState = engine.state
                        editMode = !editMode
                        paused = true
                        spedUp = false
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("build") }
            }
        }

        val verticalScrollState = rememberScrollState()
        val horizontalScrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
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

            engineState.tileGrid.draw(-1f, 0, 16 * scale, false)
            engineState.dataGrid.draw(
                1f,
                -engineState.tileGrid.rowCount * 16 * scale + 16 * scale,
                16 * scale,
                editMode
            )

            if (editMode) {
                val editGrid = engineState.dataGrid.clone()
                for (row in editGrid.grid.indices) {
                    Row(
                        modifier = Modifier
                            .zIndex(2f)
                            .graphicsLayer {
                                translationY =
                                    ((-engineState.tileGrid.rowCount * 16 * scale + 16 * scale) * 2).dp.toPx()
                            }
                    ) {
                        for (cell in editGrid.grid[row].indices) {
                            Button(
                                modifier = Modifier
                                    .size((16 * scale).dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                onClick = {
                                    if (editSelect != null && engineState.dataGrid.get(
                                            GridPosition(
                                                row,
                                                cell
                                            )
                                        )!!.passable
                                    ) {
                                        engineState.dataGrid.apply { replace(GridPosition(row, cell), editSelect!!) }
                                        engine.stopSimulation()
                                        engineState = engine.state
                                        paused = true
                                        spedUp = false
                                        editMode = false
                                    }
                                }
                            ) { }
                        }
                    }
                }
            }

            if (paused) {
                for (row in engineState.dataGrid.grid.indices) {
                    Row(
                        modifier = Modifier
                            .zIndex(2f)
                            .graphicsLayer {
                                translationY =
                                    ((-engineState.tileGrid.rowCount * 16 * scale + 16 * scale) * 2).dp.toPx()
                            }
                    ) {
                        for (cell in engineState.dataGrid.grid[row].indices) {
                            Button(
                                modifier = Modifier
                                    .size((16 * scale).dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                onClick = {
                                    if (abilitySelect != null && engineState.dataGrid.get(
                                            GridPosition(
                                                row,
                                                cell
                                            )
                                        )!! is Lecturer
                                    ) {
                                        engine.applyAbility(abilitySelect!!, GridPosition(row, cell))
                                    }
                                }
                            ) { }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun putIcon(iconName: String) {
    Icon(
        painter = BitmapPainter(
            loadImageBitmap(
                File(
                    "src/desktopMain/kotlin/lastprofstanding/res/icons/${iconName}.png"
                ).inputStream()
            )
        ),
        contentDescription = null
    )
}
