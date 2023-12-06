package lastprofstanding.engine

import lastprofstanding.engine.grid.Cell

class Ability(val movementSpeedMultiplier: Float) {
    fun apply(cell: Cell) {
        cell.movementSpeed *= movementSpeedMultiplier
    }
}