package lastprofstanding.engine.grid

/**
 * A position on the game grid in the form <x, y>
 */
typealias GridPosition = Pair<Int, Int>

operator fun GridPosition.plus(other: GridPosition): GridPosition {
    return GridPosition(first + other.first, second + other.second)
}

fun GridPosition.outOfBounds(rowCount: Int, columnCount: Int): Boolean {
    return first < 0 || first >= columnCount || second < 0 || second >= rowCount
}