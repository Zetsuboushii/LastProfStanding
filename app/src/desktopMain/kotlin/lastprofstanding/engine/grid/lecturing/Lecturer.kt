package lastprofstanding.engine.grid.lecturing

import lastprofstanding.engine.Strength
import lastprofstanding.engine.grid.Cell
import lastprofstanding.engine.grid.EmptyCell
import lastprofstanding.engine.grid.Weakness
import java.io.File

abstract class Lecturer(
        val name: String,
        movementSpeed: Float,
        weakness: Weakness<*>?,
        strength: Strength<*>?,
        spawnRate: Float?

) : Cell(false, movementSpeed, null, weakness, strength, spawnRate, true) {
        companion object {
                fun getFileForLecturer(): File {
                        return when (this::class) {
                                Kruse::class -> Kruse().getFile()
                                Stroetmann::class -> Stroetmann().getFile()
                                Hofmann::class -> Hofmann().getFile()
                                else -> EmptyCell().getFile()
                        }
                }
        }
}
