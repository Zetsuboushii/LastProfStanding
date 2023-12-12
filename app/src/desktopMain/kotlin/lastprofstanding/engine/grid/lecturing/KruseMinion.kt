package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell
import java.io.File

class KruseMinion: Cell(false, 2.5f, 5, null, null) {
    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/kruse_minion.png")
    }

    override val textRepresentation = "c"
}