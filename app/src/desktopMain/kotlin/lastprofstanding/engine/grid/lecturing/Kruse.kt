package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.SpawnPattern
import lastprofstanding.engine.grid.Weakness

class Kruse : Lecturer("Kruse", 1.3f, Weakness(HofmannMinion::class, 10, 5), null) {
    override val textRepresentation = "K"
    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        TODO("Not yet implemented")
    }
}