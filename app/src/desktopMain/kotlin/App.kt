/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import lastprofstanding.engine.*
import lastprofstanding.engine.grid.*
import java.io.FileInputStream

class MyCell : Cell(true, 1f, null, null, 10f) {
    override val textRepresentation: String = "M"
    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern {
        return createSpawnPattern(position, Pair(GridPosition(0, -3), MyCell()))
    }

    override fun getDrawableTexture(): FileInputStream {
        TODO("Not yet implemented")
    }

    override fun clone(): MyCell {
        return MyCell().apply {
            straightMovementCounter = this@MyCell.straightMovementCounter
            currentMovement = this@MyCell.currentMovement
            stepsSurvived = this@MyCell.stepsSurvived
        }
    }
}

@Composable
fun App() {
    val engine by remember { mutableStateOf(Engine.getInstance()) }
    var engineState by remember { mutableStateOf(EngineState(Grid(0, 0), StatsCounter())) }
    var didResetGrid by remember { mutableStateOf(false) }

    if (!didResetGrid) {
        engine.load(Level.BASIC)
        engineState = engine.state
        didResetGrid = true
    }

    val testmap = arrayOf(
        arrayOf(
            TileType.BOARD_TOP_LEFT,
            TileType.BOARD_TOP_MIDDLE,
            TileType.BOARD_TOP_RIGHT,
            TileType.WALL_TOP,
            TileType.WALL_TOP,
            TileType.CANVAS_TOP_LEFT,
            TileType.CANVAS_TOP_RIGHT
        ),
        arrayOf(
            TileType.BOARD_BOTTOM_LEFT,
            TileType.BOARD_BOTTOM_MIDDLE,
            TileType.BOARD_BOTTOM_RIGHT,
            TileType.WALL_BOTTOM,
            TileType.WALL_BOTTOM,
            TileType.CANVAS_BOTTOM_LEFT,
            TileType.CANVAS_BOTTOM_RIGHT
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(
            TileType.TABLE_LEFT,
            TileType.TABLE_RIGHT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT,
            TileType.FLOOR,
            TileType.FLOOR,
            TileType.FLOOR
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(
            TileType.TABLE_LEFT,
            TileType.TABLE_RIGHT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT,
            TileType.FLOOR,
            TileType.FLOOR,
            TileType.FLOOR
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(
            TileType.TABLE_LEFT,
            TileType.TABLE_RIGHT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT,
            TileType.FLOOR,
            TileType.FLOOR,
            TileType.FLOOR
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR)
    )

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
                engine.load(Level.BASIC)
                engineState = engine.state
            }) {
                Text("Reset simulation")
            }
        }
    }) {
        engineState.grid.getTexturedRepresentation(testmap)
    }
}