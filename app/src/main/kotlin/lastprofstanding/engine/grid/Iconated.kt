package lastprofstanding.engine.grid

import java.awt.Image

interface Iconated {
    val icon: Int
}

fun Iconated.getIcon(): Image {
    TODO("Fetch image in javafx-compatible format")
}