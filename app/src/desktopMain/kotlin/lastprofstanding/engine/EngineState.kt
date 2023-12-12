package lastprofstanding.engine

import lastprofstanding.engine.grid.Grid

data class EngineState(val spriteLayer: Grid, val tileLayer: Grid, val stats: StatsCounter)