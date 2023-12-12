package lastprofstanding.engine

enum class AbilityType {
    SPEED_UP,
    SPEED_DOWN,
    SPAWN_RATE_DOWN,
    SPAWN_RATE_UP,
    MINION_SPEED_UP,
    MINION_SPEED_DOWN;


    fun getAbility(): Ability {
        return when (this) {
            SPEED_UP -> Ability(1.25f, 1f, 1f)
            SPEED_DOWN -> Ability(.80f, 1f, 1f)
            SPAWN_RATE_UP -> Ability(1f, 1.25f, 1f)
            SPAWN_RATE_DOWN -> Ability(1f, 0.8f, 1f)
            MINION_SPEED_UP -> Ability(1f, 1f, 1.25f)
            MINION_SPEED_DOWN -> Ability(1f, 1f, 0.8f)
        }
    }
}