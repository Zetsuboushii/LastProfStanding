package lastprofstanding.engine.grid

import java.io.File

class Tile(var tileType: TileType) : Cell(true, 0f, null, null, null, null) {
    override val textRepresentation = "T"

    override fun getFile(): File {
        return File(
            "src/desktopMain/kotlin/lastprofstanding/res/textures/tiles/" +
                    when (tileType) {
                        TileType.FLOOR -> "floor"
                        TileType.WALL_BOTTOM -> "wall_bottom"
                        TileType.WALL_BOTTOM_LEFT -> "wall_bl"
                        TileType.WALL_BOTTOM_RIGHT -> "wall_br"
                        TileType.WALL_TOP -> "wall_top"
                        TileType.WALL_TOP_LEFT -> "wall_tl"
                        TileType.WALL_TOP_RIGHT -> "wall_tr"
                        TileType.WALL_VERTICAL -> "wall_vertical"
                        TileType.BOARD_BOTTOM_LEFT -> "board_bl"
                        TileType.BOARD_BOTTOM_MIDDLE -> "board_bm"
                        TileType.BOARD_BOTTOM_RIGHT -> "board_br"
                        TileType.BOARD_TOP_LEFT -> "board_tl"
                        TileType.BOARD_TOP_MIDDLE -> "board_tm"
                        TileType.BOARD_TOP_RIGHT -> "board_tr"
                        TileType.CANVAS_BOTTOM_LEFT -> "canvas_bl"
                        TileType.CANVAS_BOTTOM_RIGHT -> "canvas_br"
                        TileType.CANVAS_TOP_LEFT -> "canvas_tl"
                        TileType.CANVAS_TOP_RIGHT -> "canvas_tr"
                        TileType.TABLE_LEFT -> "table_left"
                        TileType.TABLE_LEFT_DECO -> "table_left2"
                        TileType.TABLE_RIGHT -> "table_right"
                    }
                    + ".png"
        )
    }

    fun generateDataCell(): Cell {
        return when (tileType) {
            TileType.FLOOR -> EmptyCell()

            TileType.WALL_BOTTOM,
            TileType.WALL_BOTTOM_LEFT,
            TileType.WALL_BOTTOM_RIGHT,
            TileType.WALL_TOP,
            TileType.WALL_TOP_LEFT,
            TileType.WALL_TOP_RIGHT,
            TileType.WALL_VERTICAL -> WallCell()

            TileType.BOARD_BOTTOM_LEFT,
            TileType.BOARD_BOTTOM_MIDDLE,
            TileType.BOARD_BOTTOM_RIGHT,
            TileType.BOARD_TOP_LEFT,
            TileType.BOARD_TOP_MIDDLE,
            TileType.BOARD_TOP_RIGHT -> WallCell()

            TileType.CANVAS_BOTTOM_LEFT,
            TileType.CANVAS_BOTTOM_RIGHT,
            TileType.CANVAS_TOP_LEFT,
            TileType.CANVAS_TOP_RIGHT -> WallCell()

            TileType.TABLE_LEFT,
            TileType.TABLE_LEFT_DECO,
            TileType.TABLE_RIGHT -> WallCell()
        }
    }
}