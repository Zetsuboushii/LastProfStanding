package lastprofstanding.engine.grid

import kotlin.reflect.KClass

data class Weakness<WeaknessType : Cell>(val against: KClass<WeaknessType>, val cellCount: Int, val radius: Int)