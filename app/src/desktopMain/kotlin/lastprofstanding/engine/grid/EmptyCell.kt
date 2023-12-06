package lastprofstanding.engine.grid

import java.io.FileInputStream

class EmptyCell : Cell(true, 0f, null, null, null), Cloneable {
    override val textRepresentation = "O"
    override fun getDrawableTexture(): FileInputStream {
        TODO("Not yet implemented")
    }

    override fun clone(): EmptyCell {
        return EmptyCell()
    }
}