package lastprofstanding.engine.grid

abstract class Lecturer(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness?
) : Cell(false, movementSpeed, null, weakness), InteractiveCell, Iconated {
}
