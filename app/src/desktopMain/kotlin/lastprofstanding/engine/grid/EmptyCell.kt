package lastprofstanding.engine.grid

class EmptyCell: Cell(true) {
    override val icon: Int
        get() = TODO("Replace with valid icon")
    override val textRepresentation = "O"

    override fun clone(): EmptyCell {
        return EmptyCell()
    }
}