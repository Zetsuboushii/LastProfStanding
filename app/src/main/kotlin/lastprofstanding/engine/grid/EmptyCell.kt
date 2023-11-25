package lastprofstanding.engine.grid

class EmptyCell: Cell(true) {
    override val icon: Int
        get() = TODO("Not yet implemented")
    override val textRepresentation = "O"

    override fun clone(): EmptyCell {
        return EmptyCell()
    }
}