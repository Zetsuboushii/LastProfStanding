package lastprofstanding.engine

import MyCell
import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.TileType

class LevelController {
    companion object {
        fun load(level: Level): Grid {
            when(level) {
                Level.BASIC -> {
                    return Grid(20, 20).apply {
                        replace(GridPosition(15, 10), MyCell())
                    }
                }
            }
        }
    }

    val map1 = arrayOf(
        arrayOf(
            TileType.BOARD_TOP_LEFT,
            TileType.BOARD_TOP_MIDDLE,
            TileType.BOARD_TOP_RIGHT,
            TileType.WALL_TOP,
            TileType.WALL_TOP,
            TileType.CANVAS_TOP_LEFT,
            TileType.CANVAS_TOP_RIGHT
        ),
        arrayOf(
            TileType.BOARD_BOTTOM_LEFT,
            TileType.BOARD_BOTTOM_MIDDLE,
            TileType.BOARD_BOTTOM_RIGHT,
            TileType.WALL_BOTTOM,
            TileType.WALL_BOTTOM,
            TileType.CANVAS_BOTTOM_LEFT,
            TileType.CANVAS_BOTTOM_RIGHT
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(
            TileType.TABLE_LEFT,
            TileType.TABLE_RIGHT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT,
            TileType.FLOOR,
            TileType.FLOOR,
            TileType.FLOOR
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(
            TileType.TABLE_LEFT,
            TileType.TABLE_RIGHT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT,
            TileType.FLOOR,
            TileType.FLOOR,
            TileType.FLOOR
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR),
        arrayOf(
            TileType.TABLE_LEFT,
            TileType.TABLE_RIGHT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT,
            TileType.FLOOR,
            TileType.FLOOR,
            TileType.FLOOR
        ),
        arrayOf(TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR)
    )

}