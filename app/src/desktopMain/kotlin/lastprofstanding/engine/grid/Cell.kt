package lastprofstanding.engine.grid

abstract class Cell(val passable: Boolean): Iconated {
    abstract fun clone(): Cell
}