package lastprofstanding.engine.grid

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

class Grid(grid: List<List<Cell>>) : Cloneable {
    val rowCount: Int = grid.size
    val columnCount: Int = grid.getOrNull(0)?.size ?: 0
    var grid = grid.map { row -> row.toMutableList() }.toMutableList()

    constructor(
        rowCount: Int,
        columnCount: Int
    ) : this(MutableList(rowCount) { MutableList(columnCount) { EmptyCell() } })

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
    fun draw(zIndex: Float, yOffset: Int, scale: Int, editMode: Boolean) {
        for (row in grid.indices) {
            Row(
                modifier = Modifier
                    .zIndex(zIndex)
                    .graphicsLayer { translationY = yOffset.dp.toPx() }
            ) {
                for (cell in grid[row]) {
                    if (editMode) cell.drawInEditMode(scale) else cell.draw(scale)
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
        return Grid(rowCount, columnCount).apply {
            for (x in 0 until rowCount) {
                for (y in 0 until columnCount) {
                    val position = GridPosition(x, y)
                    this@Grid.get(position)?.let {
                        replace(position, it.clone())
                    }
                }
            }
        }
    }
}

