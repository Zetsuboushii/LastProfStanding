package lastprofstanding.engine

import lastprofstanding.engine.grid.lecturing.Lecturer
import kotlin.reflect.KClass

typealias LecturerCountMap = Map<KClass<out Lecturer>, Int>

fun LecturerCountMap.hasOneWinner(): Boolean {
    return values.filter { it != 0 }.size == 1
}

fun LecturerCountMap.getWinningClass(): KClass<out Lecturer>? {
    return if (hasOneWinner()) {
        keys.toList()[0]
    } else {
        null
    }
}

