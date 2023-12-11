package lastprofstanding.engine.grid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import lastprofstanding.engine.grid.lecturing.Lecturer
import java.io.File

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
    fun draw(zIndex: Float, yOffset: Int, editMode: Boolean) {
        Column(
            Modifier.zIndex(zIndex).offset(0.dp, yOffset.dp)
        ) {
            for (row in grid.indices) {
                Row {
                    for (cell in grid[row]) {
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

