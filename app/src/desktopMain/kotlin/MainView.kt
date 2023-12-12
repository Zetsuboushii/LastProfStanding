import androidx.compose.runtime.*
import lastprofstanding.engine.EngineState
import lastprofstanding.engine.StatsCounter

typealias RouteCallback = (NavController.Route, EngineState?) -> Unit

@Composable
fun MainView() {
    var route by remember { mutableStateOf(NavController.Route.START_SCREEN) }
    var stats by remember { mutableStateOf(StatsCounter()) }
    val routeCallback: RouteCallback = { newRoute, state ->
        route = newRoute
        state?.stats?.let { newStats ->
            stats = newStats
        }
    }
    when (route) {
        NavController.Route.START_SCREEN -> {
            StartView(routeCallback)
        }

        NavController.Route.GAME_SCREEN -> {
            GameView(routeCallback)
        }

        NavController.Route.END_SCREEN -> {
            EndView(routeCallback, stats)
        }
    }
}