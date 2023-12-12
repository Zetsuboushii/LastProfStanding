package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import java.io.File

class KruseMinion : Cell(false, 1f, 5, null, Strength(friendlyCell = Kruse::class), null, true) {
    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/kruse_minion.png")
    }

    override fun clone(): Cell {
        return KruseMinion().apply {
            set(
                this@KruseMinion.stepsSurvived,
                this@KruseMinion.currentMovement,
                this@KruseMinion.movementSpeed,
                this@KruseMinion.spawnRate,
                this@KruseMinion.activeAbility
            )
        }
    }

    override val textRepresentation = "c"
}