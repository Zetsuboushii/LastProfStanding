package lastprofstanding.engine

import lastprofstanding.engine.grid.Grid

typealias SimulationStepCallback = (Grid, StatsCounter) -> Unit
