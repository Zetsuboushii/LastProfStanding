package lastprofstanding.engine

enum class AbilityType {
    EXTRA_SPEED,
    SLOWNESS;

    fun getAbility(): Ability {
        return when (this) {
            EXTRA_SPEED -> Ability(1.7f)
            SLOWNESS -> Ability(.4f)
        }
    }
}