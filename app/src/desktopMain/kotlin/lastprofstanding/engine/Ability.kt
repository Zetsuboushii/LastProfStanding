package lastprofstanding.engine

import lastprofstanding.engine.grid.Cell

class Ability(
    val movementSpeedMultiplier: Float,
    val spawnRateMultiplier: Float?,
) {
    fun apply(cell: Cell) {
        cell.movementSpeed *= movementSpeedMultiplier
        if (cell.spawnRate != null) {
            if (spawnRateMultiplier == null) {
                cell.spawnRate = null
            } else {
                cell.spawnRate = cell.spawnRate!! * spawnRateMultiplier // Will only be called from one thread anyway
            }
        }
    }

}