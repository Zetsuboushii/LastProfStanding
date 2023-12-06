package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.SpawnPattern
import lastprofstanding.engine.grid.Weakness

class Stroetmann: Lecturer("Stroetmann",1.1f,Weakness(KruseMinion::class,5,3),4f) {
    override val textRepresentation = "S"
    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        TODO("Not yet implemented")
    }
}