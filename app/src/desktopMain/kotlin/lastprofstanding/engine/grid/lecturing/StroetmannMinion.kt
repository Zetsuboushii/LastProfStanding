package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import lastprofstanding.forceResourceStream
import java.io.InputStream

class StroetmannMinion : Cell(false, 0.8f, 15, null, Strength(1, Stroetmann::class), null, true) {
    override fun getInputStream(): InputStream {
        return forceResourceStream("textures/sprites/stroetmann_minion.png")
    }

    override fun clone(): Cell {
        return StroetmannMinion().apply {
            set(
                this@StroetmannMinion.stepsSurvived,
                this@StroetmannMinion.currentMovement,
                this@StroetmannMinion.movementSpeed,
                this@StroetmannMinion.spawnRate,
                this@StroetmannMinion.activeAbility
            )
        }
    }

    override val textRepresentation = "p"
}