package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell
import java.io.File

class HofmannMinion : Cell(false, 0.5f, 20, null, null, null) {
    override fun getFile(): File {
        return File("src/desktopMain/kotlin/lastprofstanding/res/textures/sprites/hofmann_minion.png")
    }

    override fun clone(): Cell {
        return HofmannMinion().apply { set(stepsSurvived, currentMovement, movementSpeed, spawnRate, activeAbility) }
    }

    override val textRepresentation = "e"
}