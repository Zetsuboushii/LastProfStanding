package lastprofstanding

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import lastprofstanding.ui.MainView
import java.io.FileNotFoundException
import java.io.InputStream

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Last Prof. Standing", resizable = true) {
        MainView()
    }
}

fun forceResourceStream(path: String): InputStream {
    return object {}.javaClass.getResourceAsStream("/" + path) ?: throw FileNotFoundException("File '$path' not found.")
}