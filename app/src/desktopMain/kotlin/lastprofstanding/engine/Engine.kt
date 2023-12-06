package lastprofstanding.engine

import lastprofstanding.engine.grid.*
import kotlin.math.max
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
            val divisor = when (speed) {
                SimulationSpeed.PAUSED -> throw Exception("Simulation paused. It makes no sense to get a sleep interval.")
                SimulationSpeed.X1 -> 1
                SimulationSpeed.X2 -> 2
            }.toFloat()
            // 1 step/s for X1
            val targetPeriod = 1000f / divisor
            return max(targetPeriod - stepTime, 0f)
        }
    }


    private var current: Grid
    private var previous: Grid
    private var stats = StatsCounter()
    private lateinit var simulationStepCallback: SimulationStepCallback
    private var runningSimulation = false

    init {
        current = Grid(0, 0)
        previous = Grid(0, 0)
    }

    val state: EngineState
        get() = EngineState(previous, stats)

    fun load(level: Level) {
        val grid = LevelController.load(level)
        current = grid
        previous = grid.clone()
        stats = StatsCounter()
    }

    fun startSimulation(speed: SimulationSpeed, callback: SimulationStepCallback) {
        if (runningSimulation) {
            return
        }
        simulationStepCallback = callback
        runningSimulation = true
        if (speed == SimulationSpeed.PAUSED) {
            // Unpause requires calling `startSimulation` again
            runningSimulation = false
            return
        }
        Thread {
            simulationLoop(speed)
        }.start()
    }

    private fun simulationLoop(speed: SimulationSpeed) {
        while (runningSimulation) {
            val timeRequired = measureTime {
                stats += performSimulationStep()
                simulationStepCallback(state)
            }.inWholeMilliseconds.toFloat()
            val sleepInterval = getSleepInterval(timeRequired, speed)
            stats.timeSpentPlaying += timeRequired + sleepInterval
            Thread.sleep(sleepInterval.toLong())
        }
    }

    fun stopSimulation() {
        runningSimulation = false
    }

    fun applyAbility(ability: AbilityType, position: GridPosition) {
        previous.get(position)?.let { cell: Cell ->
            ability.getAbility().apply(cell)
        }
    }

    private fun performSimulationStep(): StatsCounter {
        for (x in 0 until previous.columnCount) {
            for (y in 0 until previous.rowCount) {
                val position = GridPosition(x, y)
                previous.get(position)?.let { cell: Cell ->
                    spawnNewCellsForCell(cell, position)
                    moveCellAppropriately(cell, position)
                    eliminateCellIfLifetimeOver(cell, position)
                    evaluateWeakness(cell, position)
                }
            }
        }
        previous = current.clone()
        return StatsCounter(0f, 0) // TODO: Update stats counter in step
    }

    private fun spawnNewCellsForCell(
        spawningCell: Cell,
        position: GridPosition
    ) {
        if (spawningCell.testForSpawningNewCells(previous, position)) {
            spawningCell.getSpawnPattern(previous, position)?.let { pattern: SpawnPattern ->
                for ((pos, cell) in pattern) {
                    spawnCellIfWithinBounds(cell, pos)
                }
            }
        }
    }

    private fun moveCellAppropriately(cell: Cell, position: GridPosition) {
        val newPos = cell.getMovementData(previous, position)
        moveCellIfPossible(cell, position, newPos)
    }

    /**
     * Spawn cell if the `position` is within bounds and points to empty cell. Overwrites empty cells, but no others.
     * @param cell cell to spawn
     * @param position position to spawn the new cell at
     */
    private fun spawnCellIfWithinBounds(cell: Cell, position: GridPosition) {
        if (position.outOfBounds(current.rowCount, current.columnCount)) {
            return
        }
        val existingCell = previous.get(position)
        if (existingCell is EmptyCell) {
            current.replace(position, cell)
        }
    }

    private fun moveCellIfPossible(cell: Cell, currentPosition: GridPosition, newPosition: GridPosition) {
        if (!newPosition.outOfBounds(previous.rowCount, previous.columnCount)) {
            previous.get(newPosition)?.let {
                if (it.passable) {
                    current.replace(newPosition, cell)
                    current.replace(currentPosition, EmptyCell())
                    cell.straightMovementCounter += 1
                }
            }
        }
    }

    private fun eliminateCellIfLifetimeOver(cell: Cell, position: GridPosition) {
        cell.stepsSurvived += 1
        if (cell.evaluateWhetherLifetimeOver()) {
            current.replace(position, EmptyCell())
        }
    }

    private fun evaluateWeakness(cell: Cell, position: GridPosition) {
        cell.weakness?.let { weakness: Weakness<*> ->
            if (cell.countCells(previous, position, weakness.radius, weakness.against) >= weakness.cellCount) {
                current.replace(position, EmptyCell())
            }
        }
    }
}