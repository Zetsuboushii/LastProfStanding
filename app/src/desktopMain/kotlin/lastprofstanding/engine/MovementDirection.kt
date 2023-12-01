package lastprofstanding.engine

import lastprofstanding.engine.grid.GridPosition

enum class MovementDirection {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;

    fun getPositionDelta(): GridPosition {
        return when (this) {
            LEFT -> GridPosition(-1, 0)
            TOP -> GridPosition(0, -1)
            RIGHT -> GridPosition(1, 0)
            BOTTOM -> GridPosition(0, 1)
        }
    }

    fun turnRight(): MovementDirection {
        return when (this) {
            LEFT -> TOP
            TOP -> RIGHT
            RIGHT -> BOTTOM
            BOTTOM -> LEFT
        }
    }
}