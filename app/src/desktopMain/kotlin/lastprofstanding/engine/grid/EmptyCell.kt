package lastprofstanding.engine.grid

class EmptyCell : Cell(true, 0f, null, null, null, null, false), Cloneable {
    override val textRepresentation = "O"
    override fun clone(): EmptyCell {
        return EmptyCell()
    }
}