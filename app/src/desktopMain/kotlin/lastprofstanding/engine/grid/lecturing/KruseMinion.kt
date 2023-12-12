package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class KruseMinion : Cell(false, 2.5f, 5, null, null, null) {

    override fun clone(): Cell {
        return KruseMinion().apply { set(stepsSurvived, currentMovement, movementSpeed, spawnRate, activeAbility) }
    }

    override val textRepresentation = "c"
}