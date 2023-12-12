package lastprofstanding.engine.grid

class WallCell : Cell(false, 0f, null, null, null, null, false) {
    override val textRepresentation = "X"

    override fun clone(): WallCell {
        return WallCell()
    }
}