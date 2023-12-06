package lastprofstanding.engine.grid

abstract class Lecturer(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness?,
        spawnRate: Float
) : Cell(false, movementSpeed, null, weakness, spawnRate), Iconated {
}
