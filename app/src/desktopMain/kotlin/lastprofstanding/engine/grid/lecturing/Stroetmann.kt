package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*

class Stroetmann: Lecturer("Stroetmann",1.1f,Weakness(KruseMinion::class,5,3),4f) {
    override fun clone(): Cell {
        return Stroetmann().apply {
            movementSpeed = this@Stroetmann.movementSpeed
            straightMovementCounter = this@Stroetmann.straightMovementCounter
            currentMovement = this@Stroetmann.currentMovement
            stepsSurvived = this@Stroetmann.stepsSurvived
            spawnRate = this@Stroetmann.spawnRate
        }
    }
    override val icon: Int
        get() = TODO("Not yet implemented")

    override val textRepresentation: String
        get() = "S"

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        TODO("Not yet implemented")
    }
}