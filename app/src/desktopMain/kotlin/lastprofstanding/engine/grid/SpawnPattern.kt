package lastprofstanding.engine.grid

/**
 * The pattern a cell might spawn other cells by. Uses absolute positions to the cell that spawns the other cells. Might be out-of-bounds.
 */
typealias SpawnPattern = List<Pair<GridPosition, Cell>>

fun createSpawnPattern(
    centralPosition: GridPosition,
    vararg cells: Pair<GridPosition, Cell>
): SpawnPattern {
    return cells.map { (position, cell) ->
        Pair(centralPosition + position, cell)
    }
}