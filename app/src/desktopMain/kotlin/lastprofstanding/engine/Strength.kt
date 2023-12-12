package lastprofstanding.engine

import lastprofstanding.engine.grid.Cell
import kotlin.reflect.KClass

class Strength<FriendlyCellType : Cell>(
    val radius: Int = 1,
    val friendlyCell: KClass<FriendlyCellType>,
    val treshold: Int = 1
)