import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import lastprofstanding.engine.StatsCounter

@Composable
fun EndView(routeCallback: RouteCallback, stats: StatsCounter) {
    Column {
        Text("End view! Stats: $stats")
        Button(onClick = {
            routeCallback.invoke(NavController.Route.START_SCREEN, null)
        }) {
            Text("Back to start screen")
        }
    }
}