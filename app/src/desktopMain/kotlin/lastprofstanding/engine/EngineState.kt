package lastprofstanding.engine

import lastprofstanding.engine.grid.Grid

data class EngineState(val dataGrid: Grid, val tileGrid: Grid, val stats: StatsCounter)