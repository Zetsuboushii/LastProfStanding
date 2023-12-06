package lastprofstanding.engine.grid

import androidx.compose.runtime.Composable

class WallCell : Cell(false) {
    override val spriteId: String
        get() = TODO("Not yet implemented")

    override val textRepresentation = "X"

    override fun clone(): WallCell {
        return WallCell()
    }
}