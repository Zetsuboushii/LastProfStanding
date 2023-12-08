package lastprofstanding.engine.grid

class EmptyCell : Cell(true, 0f, null, null, null), Cloneable {
    override val textRepresentation = "O"
    override fun clone(): EmptyCell {
        return EmptyCell()
    }
}