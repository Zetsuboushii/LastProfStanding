package lastprofstanding.engine.grid

import lastprofstanding.engine.grid.lecturing.HofmannMinion
import lastprofstanding.engine.grid.lecturing.KruseMinion
import lastprofstanding.engine.grid.lecturing.StroetmannMinion
import kotlin.reflect.KClass

data class Weakness<WeaknessType : Cell>(val against: KClass<WeaknessType>, val cellCount: Int, val radius: Int)