package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.*
import java.io.File
import kotlin.math.pow

class Hofmann : Lecturer("Hofmann", 0.1f, Weakness(StroetmannMinion::class, 2, 2), null, 16f) {
    override val textRepresentation = "H"

    override fun clone(): Cell {
        return Hofmann().apply {
            set(
                this@Hofmann.stepsSurvived,
                this@Hofmann.currentMovement,
                this@Hofmann.movementSpeed,
                this@Hofmann.spawnRate,
                this@Hofmann.activeAbility
            )
        }
    }

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
        val meanLifetime = 210 // ticks (secs at 1x)
        // (1 - p)^mean = 0.5
        // => p = 1 - 1/(2^(1/mean))
        val probability = 1 - 1 / 2.0.pow(1.0 / meanLifetime)
        return Math.random() <= probability
    }
}