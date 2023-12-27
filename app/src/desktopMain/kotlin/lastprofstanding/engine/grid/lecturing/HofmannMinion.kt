package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import lastprofstanding.forceResourceStream
import java.io.InputStream

class HofmannMinion : Cell(false, 0.5f, 20, null, Strength(friendlyCell = Hofmann::class), null, true) {
    override fun getInputStream(): InputStream {
        return forceResourceStream("textures/sprites/hofmann_minion.png")
    }

    override fun clone(): Cell {
        return HofmannMinion().apply {
            set(
                this@HofmannMinion.stepsSurvived,
                this@HofmannMinion.currentMovement,
                this@HofmannMinion.movementSpeed,
                this@HofmannMinion.spawnRate,
                this@HofmannMinion.activeAbility
            )
        }
    }

    override val textRepresentation = "e"
}