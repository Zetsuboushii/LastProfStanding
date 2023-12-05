package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.Iconated
import lastprofstanding.engine.grid.InteractiveCell
import lastprofstanding.engine.grid.Weakness

abstract class Lecturer<SpawnCellType : Cell>(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness?
) : Cell(false, movementSpeed, null, weakness), InteractiveCell<SpawnCellType>, Iconated {
}
