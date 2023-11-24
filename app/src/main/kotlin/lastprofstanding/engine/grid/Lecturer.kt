package lastprofstanding.engine.grid

abstract class Lecturer<SpawnCellType : Cell>(
        val name: String,
        passable: Boolean
) : Cell(passable), InteractiveCell<SpawnCellType> {
}
