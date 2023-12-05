package lastprofstanding.engine.grid

typealias SpawnPattern = List<Pair<GridPosition, Cell>>

fun createSpawnPattern(
    centralPosition: GridPosition,
    vararg cells: Pair<GridPosition, Cell>
): SpawnPattern {
    return cells.map { (position, cell) ->
        Pair(centralPosition + position, cell)
    }
}