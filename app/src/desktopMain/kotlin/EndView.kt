import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import lastprofstanding.engine.StatsCounter
import java.time.Duration

@Composable
fun EndView(routeCallback: RouteCallback, stats: StatsCounter) {
    val duration = Duration.ofMillis(stats.timeSpentPlaying.toLong())
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column(Modifier.background(Color.Cyan).padding(10.dp)) {
            Row {
                Text("Time spent playing:")
                Text("${duration.toMinutesPart()}:${duration.toSecondsPart()}")
            }
            Row {
                Text("Lecturers died:")
                Text(stats.lecturersDied.toString())
            }
        }
        Button(onClick = {
            routeCallback.invoke(NavController.Route.START_SCREEN, null)
        }) {
            Text("Back to start screen")
        }
    }
}