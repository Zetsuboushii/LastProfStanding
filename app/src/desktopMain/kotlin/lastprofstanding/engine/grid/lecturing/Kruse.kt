package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*

class Kruse :Lecturer("Kruse", 1.3f, Weakness(HofmannMinion,10,5), null) {
    override fun clone(): Cell {
        return Hofmann().apply {
            movementSpeed = this@Kruse.movementSpeed
            straightMovementCounter = this@Kruse.straightMovementCounter
            currentMovement = this@Kruse.currentMovement
            stepsSurvived = this@Kruse.stepsSurvived
            spawnRate = this@Kruse.spawnRate
        }
    }

    override val icon: Int
    get() = TODO("Not yet implemented")
    override val textRepresentation: String
    get() = "K"

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        TODO("Not yet implemented")
    }
}