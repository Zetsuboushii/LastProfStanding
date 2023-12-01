package lastprofstanding.engine.grid

import kotlin.reflect.KClass

data class Weakness(val against: KClass<Cell>, val cellCount: Int, val radius: Int)