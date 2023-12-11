package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*
import java.io.File
import kotlin.math.E
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

class Hofmann : Lecturer("Hofmann", 0.1f, Weakness(StroetmannMinion::class, 2, 2), null) {
    override val textRepresentation = "H"

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern {
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

    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/hofmann.png")
    }

    override fun checkIfDying(grid: Grid, position: GridPosition): Boolean {
        val mean = 3.5
        val stdDev = 1.5
        val t = stepsSurvived / 60.0 // as 1X = 1 step/sec
        val probability = -2 * (t - mean) / (stdDev.pow(3) * sqrt(PI)) * E.pow(-((t - mean) / stdDev).pow(2))
        return if (Math.random() <= probability) {
            true
        } else {
            false
        }
    }
}