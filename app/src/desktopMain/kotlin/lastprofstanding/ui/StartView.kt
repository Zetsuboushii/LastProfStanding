package lastprofstanding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import lastprofstanding.forceResourceStream
import kotlin.system.exitProcess

@Composable
fun StartView(routeProvider: RouteCallback) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = BitmapPainter(
                getImage(
                    forceResourceStream("images/title_background.png")
                )
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .blur(15.dp, 15.dp)
                .alpha(0.75f)
                .offset(0.dp, 100.dp)
        )

        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Last Prof. Standing", fontSize = 3.em, fontWeight = FontWeight.Bold)
                Button(onClick = { routeProvider.invoke(NavController.Route.GAME_SCREEN, null) }) {
                    Text("Start")
                }
                Button(onClick = {
                    exitProcess(0)
                }) {
                    Text("Quit")
                }
            }
        }
    }
}