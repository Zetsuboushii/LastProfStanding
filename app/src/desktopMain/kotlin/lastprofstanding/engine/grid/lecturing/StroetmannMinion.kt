package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell
import java.io.File

class StroetmannMinion: Cell(false, 1.5f, 10, null, null, null) {
    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/stroetmann_minion.png")

    override fun clone(): Cell {
        return StroetmannMinion().apply { set(stepsSurvived, currentMovement, movementSpeed, spawnRate, activeAbility) }
    }

    override val textRepresentation = "p"
}