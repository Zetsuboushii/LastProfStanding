package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*
import java.io.File

class Stroetmann: Lecturer("Stroetmann",1.1f,Weakness(KruseMinion::class,5,3),4f) {
    override val textRepresentation = "S"

    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/stroetmann.png")
    }

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        return createSpawnPattern(
            position,
            Pair(GridPosition(-1, 1), StroetmannMinion()),
            Pair(GridPosition(1, 1), StroetmannMinion()),
            Pair(GridPosition(-1, -1), StroetmannMinion()),
            Pair(GridPosition(1, -1), StroetmannMinion()),
        )
    }
}