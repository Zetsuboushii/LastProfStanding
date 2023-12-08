package lastprofstanding.engine

import lastprofstanding.engine.grid.Cell

class Ability(val movementSpeedMultiplier: Float, val spawnRateMultiplier: Float, sound: Sound.SoundFile?) {
    fun apply(cell: Cell) {
        cell.movementSpeed *= movementSpeedMultiplier
        if (cell.spawnRate != null) {
            cell.spawnRate = cell.spawnRate!! * spawnRateMultiplier // Will only be called from one thread anyway
        }
    }

    fun revert(cell: Cell) {
        cell.movementSpeed /= movementSpeedMultiplier
        if (cell.spawnRate != null) {
            cell.spawnRate = cell.spawnRate!! / spawnRateMultiplier
        }
    }
}