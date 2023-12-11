package lastprofstanding.engine

import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition
import lastprofstanding.engine.grid.Tile
import lastprofstanding.engine.grid.TileType
import lastprofstanding.engine.grid.lecturing.Hofmann
import lastprofstanding.engine.grid.lecturing.Kruse
import lastprofstanding.engine.grid.lecturing.Stroetmann

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
                    TileType.BOARD_TOP_MIDDLE,
                    TileType.BOARD_TOP_RIGHT,
                    TileType.WALL_TOP,
                    TileType.WALL_TOP,
                    TileType.CANVAS_TOP_LEFT,
                    TileType.CANVAS_TOP_RIGHT
                ),
                listOf(
                    TileType.BOARD_BOTTOM_LEFT,
                    TileType.BOARD_BOTTOM_MIDDLE,
                    TileType.BOARD_BOTTOM_RIGHT,
                    TileType.WALL_BOTTOM,
                    TileType.WALL_BOTTOM,
                    TileType.CANVAS_BOTTOM_LEFT,
                    TileType.CANVAS_BOTTOM_RIGHT
                ),
                listOf(
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.TABLE_LEFT,
                    TileType.TABLE_RIGHT,
                    TileType.TABLE_LEFT_DECO,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.TABLE_LEFT,
                    TileType.TABLE_RIGHT,
                    TileType.TABLE_LEFT_DECO,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
                    TileType.TABLE_LEFT,
                    TileType.TABLE_RIGHT,
                    TileType.TABLE_LEFT_DECO,
                    TileType.TABLE_RIGHT,
                    TileType.FLOOR,
                    TileType.FLOOR,
                    TileType.FLOOR
                ),
                listOf(
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