package lastprofstanding.engine

import NavController
import RouteCallback

abstract class StateDetectionRule {
    companion object {
        val oneLecturerRemaining = object : StateDetectionRule() {
            override fun testForActivation(state: EngineState): Boolean {
                val lecturerCounts = state.spriteLayer.getLecturerCountMap()
                return lecturerCounts.values.filter { it == 0 }.size == 1
            }

            override fun apply(routeCallback: RouteCallback, state: EngineState) {
                Sound.getInstance().play(Sound.SoundFile.SETLX_SOUNDTRACK)
                routeCallback.invoke(NavController.Route.END_SCREEN, state)
            }
        }

        fun getAll(): Array<StateDetectionRule> {
            return arrayOf(
                oneLecturerRemaining
            )
        }
    }

    abstract fun testForActivation(state: EngineState): Boolean

    abstract fun apply(routeCallback: RouteCallback, state: EngineState)
}