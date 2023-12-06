package lastprofstanding.engine

import lastprofstanding.engine.grid.Grid

data class EngineState(val grid: Grid, val stats: StatsCounter)