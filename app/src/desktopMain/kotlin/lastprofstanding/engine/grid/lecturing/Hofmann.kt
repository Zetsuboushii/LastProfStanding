package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*

class Hofmann : Lecturer("Hofmann", 0.1f, Weakness(StroetmannMinion::class, 2, 2), null) {
    override val textRepresentation = "H"

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        return createSpawnPattern(
            position,
            Pair(GridPosition(-2, -2), HofmannMinion()),
            Pair(GridPosition(0, -2), HofmannMinion()),
            Pair(GridPosition(2, -2), HofmannMinion()),
            Pair(GridPosition(-2, 0), HofmannMinion()),
            Pair(GridPosition(2, 0), HofmannMinion()),
            Pair(GridPosition(-2, 2), HofmannMinion()),
            Pair(GridPosition(0, 2), HofmannMinion()),
            Pair(GridPosition(2, 2), HofmannMinion()),
        )
    }
}