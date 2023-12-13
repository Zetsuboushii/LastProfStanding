
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lastprofstanding.engine.EngineState
import lastprofstanding.engine.grid.lecturing.Lecturer
import java.time.Duration

@Composable
fun EndView(routeCallback: RouteCallback, state: EngineState?) {
    var duration: Duration? = null
    state?.stats?.let { stats ->
        duration = Duration.ofMillis(stats.timeSpentPlaying.toLong())
    }
    val lecturerCountMap = state?.spriteLayer?.getLecturerCountMap() ?: mapOf()
    val oneWinner = lecturerCountMap.values.filter { it != 0 }.size == 1
    val winningClass = if (oneWinner) {
        lecturerCountMap.keys.toList()[0]
    } else {
        null
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            if (oneWinner) {
                Text("${winningClass?.simpleName} won!")
                Image(
                    painter = BitmapPainter(loadImageBitmap(Lecturer.getFileForLecturer().inputStream())),
                    contentDescription = null
                )
            } else {
                Text("Draw!")
            }
        }
        Column(Modifier.background(Color.Cyan).padding(10.dp)) {
            Row {
                Text("Time spent playing:")
                Text("${duration?.toMinutesPart()}:${duration?.toSecondsPart()}")
            }
            Row {
                Text("Lecturers died:")
                Text(state?.stats?.lecturersDied.toString())
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