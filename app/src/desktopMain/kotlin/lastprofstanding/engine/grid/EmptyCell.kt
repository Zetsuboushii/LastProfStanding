package lastprofstanding.engine.grid

import androidx.compose.runtime.Composable

class EmptyCell: Cell(true) {
    override val spriteId: String
        get() = TODO("Not yet implemented")
    @get:Composable
    override val sprite: Unit
        get() = TODO("Replace with valid icon")
    override val textRepresentation = "O"

    override fun clone(): EmptyCell {
        return EmptyCell()
    }
}