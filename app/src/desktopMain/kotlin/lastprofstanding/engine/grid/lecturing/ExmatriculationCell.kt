package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell

class ExmatriculationCell : Cell(false, 1f, 5, null) {
    override val icon: Int
        get() = TODO("Not yet implemented")

    override fun clone(): Cell {
        return ExmatriculationCell()
    }

    override val textRepresentation: String
        get() = "e"
}