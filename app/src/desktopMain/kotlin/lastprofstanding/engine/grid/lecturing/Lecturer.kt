package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.EmptyCell
import lastprofstanding.engine.grid.Weakness
import java.io.InputStream
import kotlin.reflect.KClass

abstract class Lecturer(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness<*>?,
        strength: Strength<*>?,
        spawnRate: Float?

) : Cell(false, movementSpeed, null, weakness, strength, spawnRate, true) {
        companion object {
            fun getInputStreamForLecturer(lecturer: KClass<out Lecturer>): InputStream {
                        return when (lecturer) {
                            Kruse::class -> Kruse().getInputStream()
                            Stroetmann::class -> Stroetmann().getInputStream()
                            Hofmann::class -> Hofmann().getInputStream()
                            else -> EmptyCell().getInputStream()
                        }
                }
        }
}
