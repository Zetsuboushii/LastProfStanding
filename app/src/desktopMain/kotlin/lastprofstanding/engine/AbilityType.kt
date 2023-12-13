package lastprofstanding.engine

enum class AbilityType {
    SPEED_UP,
    SPEED_DOWN,
    INCREASE_SPAWN_RATE,
    DECREASE_SPAWN_RATE;


    fun getAbility(): Ability {
        return when (this) {
            SPEED_UP -> Ability(1.25f, 1f)
            SPEED_DOWN -> Ability(.80f, 1f)
            DECREASE_SPAWN_RATE -> Ability(1f, 1.25f)
            INCREASE_SPAWN_RATE -> Ability(1f, 0.8f)
        }
    }
}