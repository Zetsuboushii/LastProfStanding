package lastprofstanding.engine.grid

class EmptyCell : Cell(true, 0f, null, null) {
    override val icon: Int
        get() = TODO("Not yet implemented")
    override val textRepresentation = "O"

    override fun clone(): Cell {
        return EmptyCell()
    }
}