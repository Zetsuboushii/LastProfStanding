package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class KruseMinion: Cell(false, 2.5f, 5, null, null) {
    override val icon: Int
        get() = TODO("Not yet implemented")

    override fun clone(): Cell {
        return KruseMinion().apply {
            movementSpeed = this@KruseMinion.movementSpeed
            straightMovementCounter = this@KruseMinion.straightMovementCounter
            currentMovement = this@KruseMinion.currentMovement
            stepsSurvived = this@KruseMinion.stepsSurvived
            spawnRate = this@KruseMinion.spawnRate
        }
    }

    override val textRepresentation: String
        get() = "c"
}