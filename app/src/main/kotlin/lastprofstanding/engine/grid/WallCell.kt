package lastprofstanding.engine.grid

class WallCell : Cell(false, 0f, null, null) {
    override val icon = TODO("Load icon")
    override val textRepresentation = "X"

    override fun clone(): WallCell {
        return WallCell()
    }
}