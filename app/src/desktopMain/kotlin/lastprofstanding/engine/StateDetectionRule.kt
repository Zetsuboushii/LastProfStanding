package lastprofstanding.engine

import NavController
import RouteCallback
import lastprofstanding.engine.grid.lecturing.Stroetmann

abstract class StateDetectionRule {
    companion object {
        val oneLecturerRemaining = object : StateDetectionRule() {
            override fun testForActivation(state: EngineState, lecturerCountMap: LecturerCountMap): Boolean {
                return lecturerCountMap.values.filter { it != 0 }.size == 1
            }

            override fun apply(routeCallback: RouteCallback, state: EngineState, lecturerCountMap: LecturerCountMap) {
                lecturerCountMap.getWinningClass()?.let { lecturer ->
                    if (lecturer == Stroetmann::class) {
                        Sound.getInstance().play(Sound.SoundFile.SETLX_SOUNDTRACK)
                    }
                }
                routeCallback.invoke(NavController.Route.END_SCREEN, state)
            }
        }

        fun getAll(): Array<StateDetectionRule> {
            return arrayOf(
                oneLecturerRemaining
            )
        }
    }

    abstract fun testForActivation(state: EngineState, lecturerCountMap: LecturerCountMap): Boolean

    abstract fun apply(routeCallback: RouteCallback, state: EngineState, lecturerCountMap: LecturerCountMap)
}