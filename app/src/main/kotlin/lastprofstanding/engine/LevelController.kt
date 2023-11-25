package lastprofstanding.engine

import lastprofstanding.MyCell
import lastprofstanding.engine.grid.Grid
import lastprofstanding.engine.grid.GridPosition

class LevelController {
    companion object {
        fun load(level: Level): Grid {
            when(level) {
                Level.BASIC -> {
                    return Grid(20, 20).apply {
                        replace(GridPosition(5, 5), MyCell())
                    }
                }
            }
        }
    }
}