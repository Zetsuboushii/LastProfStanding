package lastprofstanding.engine

import MyCell
import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition

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
}