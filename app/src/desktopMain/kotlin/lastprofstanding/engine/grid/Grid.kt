package lastprofstanding.engine.grid

import lastprofstanding.engine.grid.lecturing.Lecturer
import kotlin.reflect.KClass

class Grid(grid: List<List<Cell>>) : Cloneable {
    val rowCount: Int = grid.getOrNull(0)?.size ?: 0
    val columnCount: Int = grid.size
    var grid = grid.map { row -> row.toMutableList() }.toMutableList()

    constructor(
        rowCount: Int,
        columnCount: Int
    ) : this(MutableList(rowCount) { MutableList(columnCount) { EmptyCell() } })

    fun initialize() {
        grid.clear()
        for (x in 0 until columnCount) {
            grid.add(mutableListOf())
            for (y in 0 until rowCount) {
                val cell = EmptyCell()
                grid[x].add(cell)
            }
        }
    }

    fun getLecturerCountMap(): Map<KClass<out Lecturer>, Int> {
        val lecturerCounts = mutableMapOf<KClass<out Lecturer>, Int>()
        for (col in grid.indices) {
            for (row in grid[col].indices) {
                val position = GridPosition(col, row)
                val cell = get(position)
                if (cell is Lecturer) {
                    lecturerCounts[cell::class as KClass<out Lecturer>] = lecturerCounts[cell::class] ?: 0
                }
            }
        }
        return lecturerCounts
    }

    fun get(position: GridPosition): Cell? {
        return grid.getOrNull(position.first)?.getOrNull(position.second)
    }

    fun replace(position: GridPosition, with: Cell) {
        grid[position.first][position.second] = with
    }

    fun delete(position: GridPosition) {
        replace(position, EmptyCell())
    }

    // TODO Remove
    // @Composable
    // fun draw(zIndex: Float, yOffset: Int, scale: Int, editMode: Boolean) {
    //     for (column in grid.indices) {
    //         Row (
    //             modifier = Modifier
    //                 .zIndex(zIndex)
    //                 .graphicsLayer { translationY = yOffset.dp.toPx() }
    //         ) {
    //             for (cell in grid[column]) {
    //                 if (editMode) cell.drawInEditMode(scale) else cell.draw(scale)
    //             }
    //         }
    //     }
    // }

    fun getTextRepresentation(): String {
        var output = ""
        for (y in 0 until rowCount) {
            for (x in 0 until columnCount) {
                output += get(GridPosition(x, y))?.textRepresentation ?: "No cell at position ($x, $y)"
            }
            output += "\n"
        }
        return output
    }

    public override fun clone(): Grid {
        val new = grid.map { col ->
            col.map { cell ->
                cell.clone()
            }
        }
        return Grid(new)
    }
}

