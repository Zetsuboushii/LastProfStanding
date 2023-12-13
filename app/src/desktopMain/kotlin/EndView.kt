import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import lastprofstanding.engine.StatsCounter
import java.time.Duration

@Composable
fun EndView(routeCallback: RouteCallback, stats: StatsCounter) {
    val duration = Duration.ofMillis(stats.timeSpentPlaying.toLong())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row {
                Text("Time spent playing:")
                Text("${duration.toMinutesPart()}:${duration.toSecondsPart()}")
            }
            Row {
                Text("Lecturers died:")
                Text(stats.lecturersDied.toString())
            }
        }
        Button(
            onClick = {
                routeCallback.invoke(NavController.Route.START_SCREEN, null)
            }) {
            putIcon("arrow_back")
            Text("Back to Start", style = TextStyle(fontSize = 20.sp))
        }
    }
}