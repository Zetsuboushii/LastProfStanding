package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class StroetmannMinion: Cell(false, 1.5f, 10, null, null) {
    override val icon: Int
        get() = TODO("Not yet implemented")

    override fun clone(): Cell {
        return StroetmannMinion().apply {
            movementSpeed = this@StroetmannMinion.movementSpeed
            straightMovementCounter = this@StroetmannMinion.straightMovementCounter
            currentMovement = this@StroetmannMinion.currentMovement
            stepsSurvived = this@StroetmannMinion.stepsSurvived
            spawnRate = this@StroetmannMinion.spawnRate
        }
    }

    override val textRepresentation: String
        get() = "p"
}