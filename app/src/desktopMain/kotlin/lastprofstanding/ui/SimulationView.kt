package lastprofstanding.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
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
import lastprofstanding.forceResourceStream
import java.io.InputStream

@Composable
fun SimulationView(routeProvider: RouteCallback) {
    val engine by remember { mutableStateOf(Engine.getInstance()) }
    var engineState by remember { mutableStateOf(EngineState(Grid(0, 0), Grid(0, 0), StatsCounter())) }
    var didResetGrid by remember { mutableStateOf(false) }

    var scale by remember { mutableIntStateOf(2) }

    var editing by remember { mutableStateOf(false) }
    var spedUp by remember { mutableStateOf(false) }
    var paused by remember { mutableStateOf(true) }

    var lecturerSelectButtonState by remember { mutableStateOf(false) }
    var lecturerSelected: Lecturer? = null
    var abilitySelectButtonState by remember { mutableStateOf(false) }
    var abilitySelected: AbilityType? = null

    if (!didResetGrid) {
        engine.load(LevelType.BASIC)
        engineState = engine.state
        didResetGrid = true
    }

    val lecturers = listOf(
        Pair(Kruse(), KruseMinion()),
        Pair(Hofmann(), HofmannMinion()),
        Pair(Stroetmann(), StroetmannMinion())
    )

    val abilities = listOf(
        Pair(AbilityType.SPEED_UP, "Speed Up"),
        Pair(AbilityType.SPEED_DOWN, "Speed Down"),
        Pair(AbilityType.INCREASE_SPAWN_RATE, "Increase Spawn Rate"),
        Pair(AbilityType.DECREASE_SPAWN_RATE, "Decrease Spawn Rate"),
    )

    Row {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .width(400.dp)
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                /*
                EDITING LECTURER SELECTION
                 */
                if (editing) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .wrapContentWidth()
                    ) {
                        item {
                            Text(text = "Select a Lecturer", fontWeight = FontWeight.Bold)
                        }

                        items(lecturers) {
                            Button(
                                enabled = lecturerSelected == null || lecturerSelected == it.first,
                                onClick = {
                                    lecturerSelectButtonState = !lecturerSelectButtonState
                                    lecturerSelected = if (lecturerSelected == null) {
                                        it.first
                                    } else {
                                        null
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (lecturerSelectButtonState) {
                                        Color.DarkGray
                                    } else {
                                        Color.LightGray
                                    }
                                ),
                                modifier = Modifier
                                    .width(250.dp)
                            ) {
                                Row {
                                    Image(
                                        painter = BitmapPainter(
                                            it.first.getImageBitmap()
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Image(
                                        painter = BitmapPainter(
                                            it.second.getImageBitmap()
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Row(modifier = Modifier.padding(start = 10.dp)) {
                                    Text(text = it.first.name, textAlign = TextAlign.Center)
                                }
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }

                /*
                APPLYING ABILITIES SELECTION
                 */
                if (paused && !editing) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .wrapContentWidth()
                    ) {
                        item {
                            Text(text = "Apply an Ability", style = TextStyle(fontWeight = FontWeight.Bold))
                        }
                        items(abilities) {
                            OutlinedButton(
                                enabled = (abilitySelected == null || abilitySelected == it.first) && paused,
                                onClick = {
                                    abilitySelectButtonState = !abilitySelectButtonState
                                    abilitySelected = if (abilitySelected == null) {
                                        it.first
                                    } else {
                                        null
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (abilitySelectButtonState && paused) {
                                        Color.DarkGray
                                    } else {
                                        Color.LightGray
                                    }
                                ),
                                modifier = Modifier
                                    .width(250.dp)
                            ) {
                                Row(modifier = Modifier.padding(start = 10.dp)) {
                                    Text(text = it.second, textAlign = TextAlign.Center)
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
                    .padding(5.dp)
                    .wrapContentWidth()
                    .height(40.dp)
                    .weight(1f, false)
            ) {
                val buttonModifier = Modifier
                    .padding(5.dp)
                    .width(40.dp)

                Button(
                    onClick = {
                        if (paused) {
                            engine.startSimulation(SimulationSpeed.X1, callback = { state ->
                                engineState = state
                            }) { route, state ->
                                routeProvider.invoke(route, state)
                                engine.stopSimulation()
                            }
                            paused = !paused
                            spedUp = false
                            abilitySelectButtonState = false
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
                                engine.startSimulation(SimulationSpeed.X2, callback = { state ->
                                    engineState = state
                                }) { route, state ->
                                    engine.stopSimulation()
                                    routeProvider.invoke(route, state)
                                }
                                spedUp = !spedUp
                                paused = false
                                lecturerSelected = null
                                lecturerSelectButtonState = false
                                abilitySelected = null
                                abilitySelectButtonState = false
                            } else {
                                engine.stopSimulation()
                                engineState = engine.state
                                spedUp = !spedUp
                                paused = false
                                lecturerSelected = null
                                lecturerSelectButtonState = false
                                abilitySelected = null
                                abilitySelectButtonState = false
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
                        lecturerSelected = null
                        lecturerSelectButtonState = false
                        abilitySelected = null
                        abilitySelectButtonState = false
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("restart") }

                Button(
                    onClick = {
                        engine.stopSimulation()
                        engineState = engine.state
                        editing = !editing
                        paused = true
                        spedUp = false
                        lecturerSelected = null
                        lecturerSelectButtonState = false
                        abilitySelected = null
                        abilitySelectButtonState = false
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("build") }

                Button(
                    onClick = {
                        routeProvider.invoke(NavController.Route.END_SCREEN, engine.state)
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("stop") }

                Button(
                    onClick = {
                        when (scale) {
                            1 -> scale = 2
                            2 -> scale = 4
                            4 -> scale = 8
                        }
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("zoom_in") }
                Button(
                    onClick = {
                        when (scale) {
                            8 -> scale = 4
                            4 -> scale = 2
                            2 -> scale = 1
                        }
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = buttonModifier
                ) { putIcon("zoom_out") }
            }
        }

        val verticalScrollState = rememberScrollState()
        val horizontalScrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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
                )
        ) {
            /*
             TILE AND SPRITE RENDERING
             */
            for (column in engineState.tileLayer.grid.indices) {
                Row {
                    for (row in engineState.tileLayer.grid[column].indices) {
                        val editModifier = if (editing) {
                            Modifier
                                .size((16 * scale).dp)
                                .zIndex(1f)
                                .graphicsLayer { this.alpha = 0.75f }
                        } else {
                            Modifier
                                .size((16 * scale).dp)
                                .zIndex(1f)
                        }
                        val editColor = if (editing) {
                            if (engineState.spriteLayer.get(GridPosition(column, row))!!.passable) {
                                Color.Green
                            } else {
                                Color.Red
                            }
                        } else {
                            Color.Transparent
                        }
                        val editColorFilter = if (editing) {
                            ColorFilter.tint(color = editColor, blendMode = BlendMode.Darken)
                        } else {
                            null
                        }

                        Box {
                            Image(
                                painter = BitmapPainter(
                                    image = getImage(engineState.tileLayer.get(GridPosition(column, row))!!)
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size((16 * scale).dp)
                                    .zIndex(0f)
                            )

                            Image(
                                painter = BitmapPainter(
                                    image = engineState.spriteLayer.get(GridPosition(column, row))!!.getImageBitmap()
                                ),
                                contentDescription = null,
                                colorFilter = editColorFilter,
                                modifier = editModifier
                            )
                            if (editing) {
                                Button(
                                    modifier = Modifier
                                        .size((16 * scale).dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                    onClick = {
                                        if (lecturerSelected != null && engineState.spriteLayer.get(
                                                GridPosition(
                                                    column,
                                                    row
                                                )
                                            )!!.passable
                                        ) {
                                            engine.spawnLecturer(GridPosition(column, row), lecturerSelected!!)
                                            engineState = engine.state
                                            engine.stopSimulation()
                                            engineState = engine.state
                                            paused = true
                                            spedUp = false
                                            editing = false
                                            lecturerSelected = null
                                            lecturerSelectButtonState = false
                                        }
                                    }
                                ) { }
                            }

                            if (paused && !editing) {
                                Button(
                                    onClick = {
                                        if (abilitySelected != null && engineState.spriteLayer.get(
                                                GridPosition(
                                                    column, row
                                                )
                                            )!! is Lecturer
                                        ) {
                                            engine.applyAbility(abilitySelected!!, GridPosition(column, row))
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                    modifier = Modifier
                                        .size((16 * scale).dp)
                                ) { }
                            }
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
            getImage(
                forceResourceStream(
                    "icons/${iconName}.png"
                )
            )
        ),
        contentDescription = null
    )
}

fun getImage(stream: InputStream): ImageBitmap {
    val bitmap = loadImageBitmap(stream)
    stream.close()
    return bitmap
}

fun getImage(cell: Cell): ImageBitmap {
    return getImage(cell.getInputStream())
}