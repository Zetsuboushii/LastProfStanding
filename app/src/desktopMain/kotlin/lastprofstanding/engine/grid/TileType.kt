package lastprofstanding.engine.grid

import java.io.File

enum class TileType {
    FLOOR,
    WALL_BOTTOM, WALL_BOTTOM_LEFT, WALL_BOTTOM_RIGHT, WALL_TOP, WALL_TOP_LEFT, WALL_TOP_RIGHT, WALL_VERTICAL,
    BOARD_BOTTOM_LEFT, BOARD_BOTTOM_MIDDLE, BOARD_BOTTOM_RIGHT, BOARD_TOP_LEFT, BOARD_TOP_MIDDLE, BOARD_TOP_RIGHT,
    CANVAS_BOTTOM_LEFT, CANVAS_BOTTOM_RIGHT, CANVAS_TOP_LEFT, CANVAS_TOP_RIGHT,
    TABLE_LEFT, TABLE_LEFT_DECO, TABLE_RIGHT;

    fun getFile(): File {
        return File(
            "src/desktopMain/kotlin/lastprofstanding/res/textures/tiles/" +
                    when (this) {
                        FLOOR -> "floor"
                        WALL_BOTTOM -> "wall_bottom"
                        WALL_BOTTOM_LEFT -> "wall_bl"
                        WALL_BOTTOM_RIGHT -> "wall_br"
                        WALL_TOP -> "wall_top"
                        WALL_TOP_LEFT -> "wall_tl"
                        WALL_TOP_RIGHT -> "wall_tr"
                        WALL_VERTICAL -> "wall_vertical"
                        BOARD_BOTTOM_LEFT -> "board_bl"
                        BOARD_BOTTOM_MIDDLE -> "board_bm"
                        BOARD_BOTTOM_RIGHT -> "board_br"
                        BOARD_TOP_LEFT -> "board_tl"
                        BOARD_TOP_MIDDLE -> "board_tm"
                        BOARD_TOP_RIGHT -> "board_tr"
                        CANVAS_BOTTOM_LEFT -> "canvas_bl"
                        CANVAS_BOTTOM_RIGHT -> "canvas_br"
                        CANVAS_TOP_LEFT -> "canvas_tl"
                        CANVAS_TOP_RIGHT -> "canvas_tr"
                        TABLE_LEFT -> "table_left"
                        TABLE_LEFT_DECO -> "table_left2"
                        TABLE_RIGHT -> "table_right"
                    }
                    + ".png"
        )
    }
}