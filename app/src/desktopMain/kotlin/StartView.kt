import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun StartView(routeProvider: RouteCallback) {
    Column {
        Text("Start View!")
        Button(onClick = {
            routeProvider.invoke(NavController.Route.GAME_SCREEN, null)
        }) {
            Text("Game view!")
        }
    }
}