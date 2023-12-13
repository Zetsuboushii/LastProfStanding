
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import lastprofstanding.engine.EngineState
import lastprofstanding.engine.Sound
import lastprofstanding.engine.getWinningClass
import lastprofstanding.engine.grid.lecturing.Lecturer
import lastprofstanding.engine.hasOneWinner
import java.time.Duration

@Composable
fun EndView(routeCallback: RouteCallback, state: EngineState?) {
    var duration: Duration? = null
    state?.stats?.let { stats ->
        duration = Duration.ofMillis(stats.timeSpentPlaying.toLong())
    }
    val lecturerCountMap = state?.spriteLayer?.getLecturerCountMap() ?: mapOf()
    val winner = lecturerCountMap.getWinningClass()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (lecturerCountMap.hasOneWinner()) {
                    Text(
                        text = "${winner?.simpleName} won!",
                        fontSize = 3.em,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = BitmapPainter(getImage(Lecturer.getFileForLecturer(winner ?: Lecturer::class))),
                        contentDescription = null
                    )
                } else {
                    Text(text = "Draw!", fontSize = 3.em, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            Column(modifier = Modifier.padding(10.dp)) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(text = "Stats", fontWeight = FontWeight.Bold)
                }
                Row {
                    Text(text = "Time spent playing:")
                    Text(text = "${duration?.toMinutesPart()}:${duration?.toSecondsPart()}")
                }
                Row {
                    Text(text = "Lecturers died:")
                    Text(text = state?.stats?.lecturersDied.toString())
                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            Button(
                onClick = {
                    Sound.getInstance().stopBackgroundPlayback()
                    routeCallback.invoke(NavController.Route.START_SCREEN, null)
                }) {
                putIcon(iconName = "arrow_back")
                Text(text = "Back to Start", style = TextStyle(fontSize = 20.sp))
            }
        }
    }
}