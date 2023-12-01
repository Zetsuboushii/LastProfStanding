package lastprofstanding.engine.grid

class EmptyCell : Cell(true, 0f, null, null) {
    override val icon: Int
        get() = TODO("Replace with valid icon")
    override val textRepresentation = "O"

    override fun clone(): Cell {
        return EmptyCell()
    }
}