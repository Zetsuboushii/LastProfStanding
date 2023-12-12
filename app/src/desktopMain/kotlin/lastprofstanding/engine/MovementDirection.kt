package lastprofstanding.engine

import lastprofstanding.engine.grid.GridPosition

enum class MovementDirection {
    LEFT,
    UP,
    RIGHT,
    DOWN,
    STAY;

    fun getPositionDelta(): GridPosition {
        return when (this) {
            LEFT -> GridPosition(-1, 0)
            UP -> GridPosition(0, -1)
            RIGHT -> GridPosition(1, 0)
            DOWN -> GridPosition(0, 1)
            STAY -> GridPosition(0,0)
        }
    }

    fun turnRight(): MovementDirection {
        return when (this) {
            LEFT -> UP
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            STAY -> STAY
        }
    }
}