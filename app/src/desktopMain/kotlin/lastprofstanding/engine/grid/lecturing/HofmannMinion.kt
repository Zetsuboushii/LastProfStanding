package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class HofmannMinion : Cell(false, 0.5f, 20, null, null) {
    override val icon: Int
        get() = TODO("Not yet implemented")

    override fun clone(): Cell {
        return HofmannMinion().apply {
            movementSpeed = this@HofmannMinion.movementSpeed
            straightMovementCounter = this@HofmannMinion.straightMovementCounter
            currentMovement = this@HofmannMinion.currentMovement
            stepsSurvived = this@HofmannMinion.stepsSurvived
            spawnRate = this@HofmannMinion.spawnRate
        }
    }

    override val textRepresentation: String
        get() = "e"
}