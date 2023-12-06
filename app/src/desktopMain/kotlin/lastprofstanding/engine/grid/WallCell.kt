package lastprofstanding.engine.grid

class WallCell : Cell(false, 0f, null, null, null) {
    override val textRepresentation = "X"

    override fun clone(): WallCell {
        return WallCell()
    }
}