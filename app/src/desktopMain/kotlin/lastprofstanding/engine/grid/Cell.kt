package lastprofstanding.engine.grid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import lastprofstanding.engine.Ability
import lastprofstanding.engine.MovementDirection
import java.io.File
import kotlin.reflect.KClass

open class Cell(
    val passable: Boolean,
    /**
     * Integer x means "move x cell(s) in one step". Non-integer values mean a random function will determine with the probability 0<p<1 whether the cell will make one further step.
     */
    var movementSpeed: Float,
    val lifetime: Int?,
    val weakness: Weakness<*>?,
    var spawnRate: Float?

) {
    open val textRepresentation = "C"

    var currentMovement = MovementDirection.LEFT
    var straightMovementCounter = 1
    var stepsSurvived = 1
    val activeAbility: Ability? = null

    companion object {
        /**
         * Calculate a concrete step value (integer) from a continuous float value.
         * Returns the integer value of the float value. If the float value is not an integer, a random function will determine with the probability 0<p<1 whether the cell will make one further step.
         */
        private fun getConcreteStepFromContinuousValue(value: Float): Int {
            val randomPortion = value % 1
            if (randomPortion == 0f) {
                return value.toInt()
            }
            return if (Math.random() < randomPortion) {
                value.toInt() + 1
            } else {
                value.toInt()
            }
        }
    }

    open fun getFile(): File? {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/air.png")
    }

    @Composable
    open fun draw(scale: Int) {
        getFile()?.let { file ->
            loadImageBitmap(file.inputStream())
        }?.let {
            BitmapPainter(image = it)
        }?.let {
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(scale.dp)
            )
        }
    }

    @Composable
    open fun drawInEditMode(scale: Int) {
        getFile()?.let { file ->
            loadImageBitmap(file.inputStream())
        }?.let {
            BitmapPainter(image = it)
        }?.let {
            val editColor = if (this.passable) {
                Color.Green
            } else {
                Color.Red
            }
            Image(
                painter = it,
                contentDescription = null,
                colorFilter = ColorFilter.tint(editColor, blendMode = BlendMode.Darken),
                modifier = Modifier
                    .size(scale.dp)
                    .graphicsLayer { this.alpha = 0.785f }
            )
        }
    }


    open fun clone(): Cell {
        return Cell(passable, movementSpeed, lifetime, weakness, spawnRate).apply {
            currentMovement = this@Cell.currentMovement
            straightMovementCounter = this@Cell.straightMovementCounter
            stepsSurvived = this@Cell.stepsSurvived
        }
    }

    open fun checkIfDying(grid: Grid, position: GridPosition): Boolean {
        return false
    }

    /**
     * Get the spawn pattern the cell spawns other cells by (relative positions).
     * Might also return `GridPosition`s that are out-of-bounds.
     * Cells will only be spawned, if `testForSpawningNewCells` returns true.
     * Default implementation: return null (no cells spawned)
     * @param grid Grid to calculate
     * @param position Position where the new cell should be spawned
     * @return Optional Spawn pattern
     */
    open fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        return null
    }

    open fun testForSpawningNewCells(grid: Grid, position: GridPosition): Boolean {
        return spawnRate?.let {
            stepsSurvived % getConcreteStepFromContinuousValue(it) == 0
        } ?: false
    }


    private fun checkMovementDirection(grid: Grid, position: GridPosition, direction: MovementDirection): Boolean {
        return grid.get(position + direction.getPositionDelta())?.passable ?: false
    }

    private fun getMovementDirection(grid: Grid, position: GridPosition): MovementDirection {
        val nextCellNotPassable = grid.get(position + currentMovement.getPositionDelta())?.passable != true
        if (nextCellNotPassable || (straightMovementCounter % 8 == 0)) {
            if (checkMovementDirection(grid, position, currentMovement.turnRight())) {
                return currentMovement.turnRight()
            }
        }
        return currentMovement
    }

    fun getMovementData(grid: Grid, position: GridPosition): GridPosition {
        val movementDirection = getMovementDirection(grid, position)
        currentMovement = movementDirection
        return position + movementDirection.getPositionDelta() * getConcreteStepFromContinuousValue(movementSpeed)
    }

    fun evaluateWhetherLifetimeOver(): Boolean {
        if (lifetime != null) {
            return stepsSurvived >= lifetime
        }
        return false
    }

    /**
     * Count the number of cells that are within the given radius (calculated as a square) around the given position and match the given predicate.
     */
    fun countCells(grid: Grid, around: GridPosition, radius: Int, predicate: (Cell, GridPosition) -> Boolean): Int {
        var counter = 0
        for (x in around.first - radius..around.first + radius) {
            for (y in around.second - radius..around.second + radius) {
                val position = GridPosition(x, y)
                grid.get(position)?.let { cell: Cell ->
                    if (predicate.invoke(cell, position)) {
                        counter += 1
                    }
                }
            }
        }
        return counter
    }

    /**
     * Count the number of cells that are within the given radius (calculated as a square) around the given position and are of the given class.
     */
    fun <CellType : Cell> countCells(grid: Grid, around: GridPosition, radius: Int, ofClass: KClass<CellType>): Int {
        return countCells(grid, around, radius) { cell: Cell, _ ->
            ofClass.isInstance(cell)
        }
    }
}