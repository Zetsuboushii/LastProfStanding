import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import kotlin.system.exitProcess

@Composable
fun StartView(routeProvider: RouteCallback) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Last Prof. Standing", fontSize = 3.em, fontWeight = FontWeight.Bold)
        Button(onClick = {
            routeProvider.invoke(NavController.Route.GAME_SCREEN, null)
        }) {
            Text("Start")
        }
        Button(onClick = {
            exitProcess(0)
        }) {
            Text("Quit")
        }
    }
}