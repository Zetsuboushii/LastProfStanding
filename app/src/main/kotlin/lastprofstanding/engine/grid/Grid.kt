package lastprofstanding.engine.grid

class Grid(rows: Int, columns: Int) {
    val rowCount: Int = rows
    val columnCount: Int = columns
    private var grid: MutableList<MutableList<Cell>> = mutableListOf()

    init {
        initialize()
    }

    fun initialize() {
        grid.clear()
        for (x in 0 until rowCount) {
            grid.add(mutableListOf())
            for (y in 0 until columnCount) {
                val cell = EmptyCell()
                grid[x].add(cell)
            }
        }
    }

    fun get(position: GridPosition): Cell {
        // Any more complicated logic to check whether within bounds or just returning Cell? would degrade performance
        // Therefore, it's better to catch any errors immediately when accessing out-of-bounds cells
        return grid[position.first][position.second]
    }

    fun replace(position: GridPosition, with: Cell) {
        grid[position.first][position.second] = with
    }

    fun delete(position: GridPosition) {
        replace(position, EmptyCell())
    }
}