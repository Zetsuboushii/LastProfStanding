package lastprofstanding.engine.grid

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap

class Tile(var tileType: TileType): Cell(true) {
    fun drawTile() {
        Image(
            painter = BitmapPainter(image = loadImageBitmap(tileType.getFile().inputStream())),
            contentDescription = null,
        )
    }
}