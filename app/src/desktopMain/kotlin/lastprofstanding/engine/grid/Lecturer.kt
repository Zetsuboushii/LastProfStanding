package lastprofstanding.engine.grid

abstract class Lecturer<SpawnCellType : Cell>(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness?
) : Cell(false, movementSpeed, null, weakness), InteractiveCell<SpawnCellType>, Iconated {
}
