package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*

class Hofmann : Lecturer("Hofmann", 0.1f, Weakness(StroetmannMinion,2,2), null) {
    override fun clone(): Cell {
        return Hofmann().apply {
            movementSpeed = this@Hofmann.movementSpeed
            straightMovementCounter = this@Hofmann.straightMovementCounter
            currentMovement = this@Hofmann.currentMovement
            stepsSurvived = this@Hofmann.stepsSurvived
            spawnRate = this@Hofmann.spawnRate
        }
    }

    override val icon: Int
        get() = TODO("Not yet implemented")
    override val textRepresentation: String
        get() = "H"

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        TODO("Not yet implemented")
    }
}