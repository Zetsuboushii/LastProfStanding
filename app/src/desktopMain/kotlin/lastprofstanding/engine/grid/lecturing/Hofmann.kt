package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.SpawnPattern
import lastprofstanding.engine.grid.Weakness

class Hofmann : Lecturer("Hofmann", 0.1f, Weakness(StroetmannMinion::class, 2, 2), null) {
    override val textRepresentation = "H"

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        TODO("Not yet implemented")
    }
}