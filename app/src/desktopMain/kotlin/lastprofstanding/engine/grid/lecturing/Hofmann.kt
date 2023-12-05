package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition

class Hofmann: Lecturer<ExmatriculationCell>("Hofmann",0.1f,null) {
    override fun clone(): Cell {
        return Hofmann().apply {
            movementSpeed = this@Hofmann.movementSpeed
            straightMovementCounter = this@Hofmann.straightMovementCounter
            currentMovement = this@Hofmann.currentMovement
            stepsSurvived = this@Hofmann.stepsSurvived
        }
    }

    override val icon: Int
        get() = TODO("Not yet implemented")
    override val textRepresentation: String
        get() = "H"

    override fun testForSpawningNewCell(grid: Grid, position: GridPosition): Pair<ExmatriculationCell, GridPosition>? {
        TODO("Not yet implemented")
    }


}