package lastprofstanding.engine.grid

import lastprofstanding.engine.Ability
import lastprofstanding.engine.MovementDirection
import lastprofstanding.engine.Strength
import java.io.File
import kotlin.reflect.KClass

abstract class Cell(
    val passable: Boolean,
    /**
     * Integer x means "move x cell(s) in one step". Non-integer values mean a random function will determine with the probability 0<p<1 whether the cell will make one further step.
     */
    var movementSpeed: Float,
    val lifetime: Int?,
    val weakness: Weakness<*>?,
    val strength: Strength<*>?,
    var spawnRate: Float?,
    val fightable: Boolean
) {
    open val textRepresentation = "Default"

    var currentMovement = MovementDirection.LEFT
    var stepsSurvived = 1
    var activeAbility: Ability? = null

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

    open fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/air.png")
    }

    // TODO Remove
    // @Composable
    // open fun draw(scale: Int) {
    //     getFile()?.let { file ->
    //         loadImageBitmap(file.inputStream())
    //     }?.let {
    //         BitmapPainter(image = it)
    //     }?.let {
    //         Box(
    //             // modifier = Modifier.zIndex(zIndex.toFloat())
    //         ) {
    //             Image(
    //                 painter = it,
    //                 contentDescription = null,
    //                 modifier = Modifier
    //                     .size(scale.dp)
    //             )
    //         }
    //     }
    // }

    // TODO Remove
    // @Composable
    // open fun drawInEditMode(scale: Int) {
    //     getFile()?.let { file ->
    //         loadImageBitmap(file.inputStream())
    //     }?.let {
    //         BitmapPainter(image = it)
    //     }?.let {
    //         val editColor = if (this.passable) {
    //             Color.Green
    //         } else {
    //             Color.Red
    //         }
    //         Image(
    //             painter = it,
    //             contentDescription = null,
    //             colorFilter = ColorFilter.tint(editColor, blendMode = BlendMode.Darken),
    //             modifier = Modifier
    //                 .size(scale.dp)
    //                 .graphicsLayer { this.alpha = 0.785f }
    //                 // .zIndex(zIndex.toFloat())
    //         )
    //     }
    // }

    fun set(
        stepsSurvived: Int,
        currentMovement: MovementDirection,
        movementSpeed: Float,
        spawnRate: Float?,
        activeAbility: Ability?
    ) {
        this.stepsSurvived = stepsSurvived
        this.currentMovement = currentMovement
        this.movementSpeed = movementSpeed
        this.spawnRate = spawnRate
        this.activeAbility = activeAbility
    }

    abstract fun clone(): Cell

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
            val spawnRate = getConcreteStepFromContinuousValue(it)
            if (spawnRate == 0) {
                return true
            }
            stepsSurvived % spawnRate == 0
        } ?: false
    }


    private fun checkMovementDirection(
        grid: Grid,
        position: GridPosition,
        direction: MovementDirection,
        distance: Int
    ): Boolean {
        return grid.get(position + direction.getPositionDelta() * distance)?.passable ?: false
    }


    private fun getMovementDirection(grid: Grid, position: GridPosition, distance: Int): MovementDirection {
        val directionsToTry = MovementDirection.entries.toMutableList()

        while (directionsToTry.isNotEmpty()) {
            val randomIndex = (0 until directionsToTry.size).random()
            val directionToTry = directionsToTry[randomIndex]

            if (checkMovementDirection(grid, position, directionToTry, distance)) {
                return directionToTry
            } else {
                directionsToTry.removeAt(randomIndex)
            }
        }

        // If all directions are blocked, return the current movement to stay in place
        return MovementDirection.STAY
    }

    fun getMovementData(grid: Grid, position: GridPosition): GridPosition {

        val distance = getConcreteStepFromContinuousValue(movementSpeed)
        val movementDirection = getMovementDirection(grid, position, distance)
        currentMovement = movementDirection
        return position + movementDirection.getPositionDelta() * distance
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