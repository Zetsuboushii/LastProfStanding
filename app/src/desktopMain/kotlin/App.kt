import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import lastprofstanding.engine.*
import lastprofstanding.engine.grid.*
import lastprofstanding.engine.grid.lecturing.Hofmann
import lastprofstanding.engine.grid.lecturing.Kruse
import lastprofstanding.engine.grid.lecturing.Stroetmann

@Composable
fun App() {
    val engine by remember { mutableStateOf(Engine.getInstance()) }
    var engineState by remember { mutableStateOf(EngineState(Grid(0, 0), Grid(0, 0), StatsCounter())) }
    var didResetGrid by remember { mutableStateOf(false) }
    var editMode by remember { mutableStateOf(false) }

    if (!didResetGrid) {
        engine.load(LevelType.BASIC)
        engineState = engine.state
        didResetGrid = true
    }

    Scaffold(topBar = {
        Row {
            Button(onClick = {
                engine.startSimulation(SimulationSpeed.X1) { state ->
                    engineState = state
                }
            }) {
                Text("Start simulation")
            }
            Button(onClick = {
                engine.stopSimulation()
                engineState = engine.state
            }) {
                Text("Stop simulation")
            }
            Button(onClick = {
                engine.load(LevelType.BASIC)
                engineState = engine.state
            }) {
                Text("Reset simulation")
            }
            Button(onClick = {
                editMode = !editMode
            }) {
                if (editMode) Text("Enter Edit Mode") else Text("End Edit Mode")
            }
            /*
            Button(onClick = {
                Sound.getInstance().play(Sound.SoundFile.ROBLOX_DEATH_SOUND)
            }) {
                Text("Sound check (1)")
            }
            Button(onClick = {
                Sound.getInstance().play(Sound.SoundFile.SETLX_SOUNDTRACK)
            }) {
                Text("Sound check (2)")
            }
            Button(onClick = {
                Sound.getInstance().stopBackgroundPlayback()
            }) {
                Text("Stop sound check")
            }
             */
        }
    }) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO: Remove
            engineState.dataGrid.apply {
                replace(GridPosition(2, 0), Hofmann())
                replace(GridPosition(9, 1), Stroetmann())
                replace(GridPosition(5, 6), Kruse())
            }
            engineState.tileGrid.get(GridPosition(0, 0))?.passable
            engineState.tileGrid.draw(0f, 0, false)
            engineState.dataGrid.draw(1f, -engineState.tileGrid.rowCount * 16, editMode)
        }
        Column {
            Text(engineState.dataGrid.getTextRepresentation())
        }
    }
}

