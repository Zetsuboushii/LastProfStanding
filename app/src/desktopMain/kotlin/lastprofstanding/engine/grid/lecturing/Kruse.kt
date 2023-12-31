package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.*
import lastprofstanding.forceResourceStream
import java.io.InputStream

class Kruse :
    Lecturer("Kruse", 1.3f, Weakness(HofmannMinion::class, 5, 3), Strength(friendlyCell = KruseMinion::class), 4f) {
    override val textRepresentation = "K"

    override fun clone(): Cell {
        return Kruse().apply {
            set(
                this@Kruse.stepsSurvived,
                this@Kruse.currentMovement,
                this@Kruse.movementSpeed,
                this@Kruse.spawnRate,
                this@Kruse.activeAbility
            )
        }
    }

    override fun getInputStream(): InputStream {
        return forceResourceStream("textures/sprites/kruse.png")
    }

    override fun getSpawnPattern(grid: Grid, position: GridPosition): SpawnPattern? {
        return createSpawnPattern(
            position,
            Pair(GridPosition(-1, -1), KruseMinion()),
            Pair(GridPosition(1, 1), KruseMinion()),
        )
    }
}