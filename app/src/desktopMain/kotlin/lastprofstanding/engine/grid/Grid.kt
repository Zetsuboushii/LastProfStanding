package lastprofstanding.engine.grid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

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

    fun get(position: GridPosition): Cell? {
        return grid.getOrNull(position.first)?.getOrNull(position.second)
    }

    fun replace(position: GridPosition, with: Cell) {
        grid[position.first][position.second] = with
    }

    fun delete(position: GridPosition) {
        replace(position, EmptyCell())
    }

    @Composable
    fun draw(zIndex: Float, yOffset: Int, editMode: Boolean) {
        Row(
            Modifier.zIndex(zIndex).offset(0.dp, yOffset.dp)
        ) {
            for (col in grid.indices) {
                Column {
                    for (cell in grid[col]) {
                        if (editMode) cell.drawInEditMode() else cell.draw()
                    }
                }
            }
        }
    }

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
        return Grid(grid).apply {
            for (x in 0 until columnCount) {
                for (y in 0 until rowCount) {
                    val position = GridPosition(x, y)
                    this@Grid.get(position)?.let {
                        replace(position, it.clone())
                    }
                }
            }
        }
    }
}

