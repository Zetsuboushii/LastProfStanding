package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import java.io.File

class HofmannMinion : Cell(false, 0.5f, 20, null, Strength(friendlyCell = Hofmann::class), null) {
    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/hofmann_minion.png")
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