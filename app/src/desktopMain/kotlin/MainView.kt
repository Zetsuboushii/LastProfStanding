import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import lastprofstanding.engine.EngineState

typealias RouteCallback = (NavController.Route, EngineState?) -> Unit

@Composable
fun MainView() {
    var route by remember { mutableStateOf(NavController.Route.START_SCREEN) }
    var state by remember { mutableStateOf<EngineState?>(null) }
    val routeCallback: RouteCallback = { newRoute, newState ->
        route = newRoute
        state = newState
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (route) {
            NavController.Route.START_SCREEN -> {
                StartView(routeCallback)
            }

            NavController.Route.GAME_SCREEN -> {
                SimulationView(routeCallback)
            }

            NavController.Route.END_SCREEN -> {
                EndView(routeCallback, state)
            }
        }
    }
}
