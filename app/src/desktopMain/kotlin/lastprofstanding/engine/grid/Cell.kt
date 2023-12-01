package lastprofstanding.engine.grid

import lastprofstanding.engine.MovementDirection
import kotlin.reflect.KClass

abstract class Cell(
    val passable: Boolean,
    /**
     * Integer x means "move x cell(s) in one step". Non-integer values mean a random function will determine with the probability 0<p<1 whether the cell will make one further step.
     */
    var movementSpeed: Float,
    val lifetime: Int?,
    val weakness: Weakness?,
) : Iconated {
    var currentMovement = MovementDirection.LEFT
    var straightMovementCounter = 1
    var stepsSurvived = 0

    abstract fun clone(): Cell

    private fun getMovementDistance(): Int {
        val randomPortion = movementSpeed % 1
        if (randomPortion == 0f) {
            return movementSpeed.toInt()
        }
        return if (Math.random() < randomPortion) {
            movementSpeed.toInt() + 1
        } else {
            movementSpeed.toInt()
        }
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
        return position + movementDirection.getPositionDelta() * getMovementDistance()
    }

    fun evaluateWhetherLifetimeOver(): Boolean {
        if (lifetime != null) {
            return stepsSurvived >= lifetime
        }
        return false
    }

    fun countCells(grid: Grid, around: GridPosition, radius: Int, predicate: (Cell, GridPosition) -> Boolean): Int {
        var counter = 0
        for (x in around.first - radius..around.first + radius) {
            for (y in around.second - radius..around.second + radius) {
                val position = GridPosition(x, y)
                grid.get(position)?.let { cell ->
                    if (predicate.invoke(cell, position)) {
                        counter += 1
                    }
                }
            }
        }
        return counter
    }

    fun countCells(grid: Grid, around: GridPosition, radius: Int, ofClass: KClass<Cell>): Int {
        return countCells(grid, around, radius) { cell, _ ->
            ofClass.isInstance(cell)
        }
    }
}