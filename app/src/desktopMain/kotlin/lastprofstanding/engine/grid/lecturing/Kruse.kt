package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*

class Kruse : Lecturer("Kruse", 1.3f, Weakness(HofmannMinion::class, 10, 5), 4f) {
    override val textRepresentation = "K"
    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        return createSpawnPattern(
            position,
            Pair(GridPosition(-1, -1), KruseMinion()),
            Pair(GridPosition(1, 1), KruseMinion()),
        )
    }
}