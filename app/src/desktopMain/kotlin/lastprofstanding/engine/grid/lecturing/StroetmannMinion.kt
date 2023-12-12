package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class StroetmannMinion : Cell(false, 1.5f, 10, null, null, null) {

    override fun clone(): Cell {
        return StroetmannMinion().apply { set(stepsSurvived, currentMovement, movementSpeed, spawnRate, activeAbility) }
    }

    override val textRepresentation = "p"
}