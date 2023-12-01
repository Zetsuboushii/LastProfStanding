package lastprofstanding.engine.grid

import java.awt.Image

interface Iconated {
    val icon: Int
    /** single-character text representation of a visual object primarily used for debugging purposes */
    val textRepresentation: String
}

fun Iconated.getIcon(): Image {
    TODO("Fetch image in compose-compatible format")
}