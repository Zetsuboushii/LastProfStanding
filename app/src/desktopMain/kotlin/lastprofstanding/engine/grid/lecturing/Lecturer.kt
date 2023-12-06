package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.Iconated
import lastprofstanding.engine.grid.Weakness

abstract class Lecturer(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness?,
        spawnRate: Float?
) : Cell(false, movementSpeed, null, weakness, spawnRate), Iconated {
}
