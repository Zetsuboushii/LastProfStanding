package lastprofstanding.engine.grid

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

interface Iconated {
    val spriteId: String

    @get:Composable
    val sprite: Unit

    /** single-character text representation of a visual object primarily used for debugging purposes */
    val textRepresentation: String
}

@Composable
fun getIcon(spriteId: String): Unit {
    @OptIn(ExperimentalResourceApi::class)
    return Image(
        painter = painterResource("lastprofstanding/res/textures/sprites/${spriteId}.png"),
        contentDescription = null
    )
}