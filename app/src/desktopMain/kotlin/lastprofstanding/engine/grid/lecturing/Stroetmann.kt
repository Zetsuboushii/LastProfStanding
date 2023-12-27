package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.*
import lastprofstanding.forceResourceStream
import java.io.InputStream

class Stroetmann : Lecturer(
    "Stroetmann",
    1f,
    Weakness(KruseMinion::class, 5, 3),
    Strength(friendlyCell = StroetmannMinion::class),
    8f
) {
    override val textRepresentation = "S"

    override fun clone(): Cell {
        return Stroetmann().apply {
            set(
                this@Stroetmann.stepsSurvived,
                this@Stroetmann.currentMovement,
                this@Stroetmann.movementSpeed,
                this@Stroetmann.spawnRate,
                this@Stroetmann.activeAbility
            )
        }
    }

    override fun getInputStream(): InputStream {
        return forceResourceStream("textures/sprites/stroetmann.png")
    }

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern {
        return createSpawnPattern(
            position,
            Pair(GridPosition(-1, 1), StroetmannMinion()),
            Pair(GridPosition(1, 1), StroetmannMinion()),
            Pair(GridPosition(-1, -1), StroetmannMinion()),
            Pair(GridPosition(1, -1), StroetmannMinion()),
        )
    }
}