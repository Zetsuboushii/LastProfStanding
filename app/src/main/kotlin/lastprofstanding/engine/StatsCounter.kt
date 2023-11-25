package lastprofstanding.engine

data class StatsCounter(var timeSpentPlaying: Float = 0f, var lecturersDied: Int = 0) {
    operator fun plus(other: StatsCounter): StatsCounter {
        return StatsCounter(timeSpentPlaying + other.timeSpentPlaying, lecturersDied + other.lecturersDied)
    }
}
