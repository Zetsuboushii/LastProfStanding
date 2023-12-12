package lastprofstanding.engine

import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.Tile
import lastprofstanding.engine.grid.TileType

class LevelController {
    data class Level(val tileGrid: Grid, val dataGrid: Grid)
    companion object {
        fun load(level: LevelType): Level {
            val tileMap = when (level) {
                LevelType.BASIC -> getBasicTileMap()
            }.map { row ->
                row.map { tileType -> Tile(tileType) }
            }
            val tileGrid = Grid(tileMap)
            val dataGrid = Grid(tileMap.map { row ->
                row.map { tile -> tile.generateDataCell() }
            })
            return Level(tileGrid, dataGrid)
        }

        private fun getBasicTileMap(): List<List<TileType>> {
            return listOf(
                listOf(
                    TileType.BOARD_TOP_LEFT,
                    TileType.BOARD_BOTTOM_LEFT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.TABLE_LEFT,
                    TileType.FLOOR,
                    TileType.TABLE_LEFT,
                    TileType.FLOOR,
                    TileType.TABLE_LEFT,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.BOARD_TOP_MIDDLE,
                    TileType.BOARD_BOTTOM_MIDDLE,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.BOARD_TOP_RIGHT,
                    TileType.BOARD_BOTTOM_RIGHT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.TABLE_LEFT_DECO,
                    TileType.FLOOR,
                    TileType.TABLE_LEFT_DECO,
                    TileType.FLOOR,
                    TileType.TABLE_LEFT_DECO,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.WALL_TOP,
                    TileType.WALL_BOTTOM,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.WALL_TOP,
                    TileType.WALL_BOTTOM,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.CANVAS_TOP_LEFT,
                    TileType.CANVAS_BOTTOM_LEFT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.CANVAS_TOP_RIGHT,
                    TileType.CANVAS_BOTTOM_RIGHT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                )
            )

        }
    }
}