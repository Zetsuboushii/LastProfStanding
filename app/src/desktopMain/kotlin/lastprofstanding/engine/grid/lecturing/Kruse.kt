package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*
import java.io.File

class Kruse : Lecturer("Kruse", 1.3f, Weakness(HofmannMinion::class, 10, 5), null) {
    override val textRepresentation = "K"

    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/kruse.png")
    }
    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        return createSpawnPattern(
            position,
            Pair(GridPosition(-1, -1), KruseMinion()),
            Pair(GridPosition(1, 1), KruseMinion()),
        )
    }
}