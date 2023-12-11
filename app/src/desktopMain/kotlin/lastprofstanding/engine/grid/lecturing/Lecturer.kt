package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.Weakness

abstract class Lecturer(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness<*>?,
        strength: Strength<*>?,
        spawnRate: Float?

) : Cell(false, movementSpeed, null, weakness, strength, spawnRate)