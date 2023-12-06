package lastprofstanding.engine.grid

import java.io.FileInputStream

class Tile(var tileType: TileType) : Cell(true, 0f, null, null, null) {
    override val textRepresentation = "T"

    override fun clone(): Cell {
        TODO("Not yet implemented")
    }

    override fun getDrawableTexture(): FileInputStream {
        return tileType.getFile().inputStream()
    }
}