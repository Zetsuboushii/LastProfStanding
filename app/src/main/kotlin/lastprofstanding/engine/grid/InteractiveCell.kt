package lastprofstanding.engine.grid

interface InteractiveCell<SpawnCellType: Cell> {
    /**
     * Test, whether a new cell should be spawned.
     * @param grid Grid to calculate
     */
    fun testForSpawningNewCell(grid: Grid, position: GridPosition): Pair<SpawnCellType,GridPosition>?
}