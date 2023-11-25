package lastprofstanding.engine

import lastprofstanding.engine.grid.*
import kotlin.time.measureTime

class Engine private constructor() {

    companion object {
        private var instance: Engine? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Engine().also { instance = it }
            }

        /**
         * Get the time the simulation thread should sleep for between steps (in milliseconds).
         * @param stepTime time required to perform one simulation step
         * @param speed the speed setting of the simulation
         */
        private fun getSleepInterval(stepTime: Float, speed: SimulationSpeed): Float {
            val divisor = when(speed) {
                SimulationSpeed.PAUSED -> throw Exception("Simulation paused. It makes no sense to get a sleep interval.")
                SimulationSpeed.X1 -> 1
                SimulationSpeed.X2 -> 2
            }.toFloat()
            // 1 step/s for X1
            val targetPeriod = 1000f / divisor
            return targetPeriod - stepTime
        }
    }

    private lateinit var current: Grid
    private lateinit var previous: Grid
    private lateinit var simulationStepCallback: SimulationStepCallback
    private var runningSimulation = false

    fun load(level: Level) {
        val grid = LevelController.load(level)
        current = grid
        previous = grid.clone()
    }

    fun startSimulation(callback: SimulationStepCallback, speed: SimulationSpeed) {
        simulationStepCallback = callback
        runningSimulation = true
        if (speed == SimulationSpeed.PAUSED) {
            // Unpause requires calling `startSimulation` again
            runningSimulation = false
            return
        }
        // TODO: Concurrency to not block UI thread
        //GlobalScope.launch {
        simulationLoopCoroutine(speed)
        //}
    }

    fun simulationLoopCoroutine(speed: SimulationSpeed) {
        //suspendCoroutine<Nothing> {
            var stats = StatsCounter()
            while(runningSimulation) {
                val timeRequired = measureTime {
                    stats += performSimulationStep()
                    simulationStepCallback(previous, stats)
                }.inWholeMilliseconds.toFloat()
                stats.timeSpentPlaying += timeRequired
                val sleepInterval = getSleepInterval(timeRequired, speed)
                Thread.sleep(sleepInterval.toLong())
            }
        //}
    }

    fun stopSimulation() {
        runningSimulation = false
    }

    private fun performSimulationStep(): StatsCounter {
        for (x in 0 until previous.columnCount) {
            for (y in 0 until previous.rowCount) {
                val position = GridPosition(x, y)
                val cell = previous.get(position)
                if (cell is InteractiveCell<*>) {
                    val spawnData = cell.testForSpawningNewCell(previous, position)
                    if (spawnData != null) {
                        spawnCellIfWithinBounds(spawnData.first, spawnData.second)
                    }
                }
            }
        }
        previous = current.clone()
        return StatsCounter(0f, 0) // TODO: Update stats counter in step
    }

    /**
     * Spawn cell if the `position` is within bounds and points to empty cell. Overwrites empty cells, but no others.
     * @param cell cell to spawn
     * @param position position to spawn the new cell at
     */
    private fun spawnCellIfWithinBounds(cell: Cell, position: GridPosition) {
        if (position.outOfBounds(current.rowCount, current.columnCount)) { return }
        val existingCell = previous.get(position)
        if (existingCell is EmptyCell) {
            current.replace(position, cell)
        }
    }
}