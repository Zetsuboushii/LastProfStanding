package lastprofstanding.engine.grid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp

class Grid(rows: Int, columns: Int) : Cloneable {
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
    fun getTexturedRepresentation(grid: Array<Array<TileType>>) {
        var offset: Int = 0
        for (row in grid.indices) {
            Row(modifier = Modifier.padding(top = offset.dp)) {
                for (cell in grid[row]) {
                    Image(
                        painter = BitmapPainter(image = loadImageBitmap(cell.getFile().inputStream())),
                        contentDescription = null,
                    )
                }
            }
            offset += 16
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

