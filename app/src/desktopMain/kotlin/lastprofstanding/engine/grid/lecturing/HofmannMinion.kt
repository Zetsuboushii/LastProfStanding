package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class HofmannMinion : Cell(false, 0.5f, 20, null, null, null) {

    override fun clone(): Cell {
        return HofmannMinion().apply { set(stepsSurvived, currentMovement, movementSpeed, spawnRate, activeAbility) }
    }

    override val textRepresentation = "e"
}