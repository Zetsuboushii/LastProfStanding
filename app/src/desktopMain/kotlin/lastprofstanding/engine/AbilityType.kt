package lastprofstanding.engine

enum class AbilityType {
    EXTRA_SPEED,
    SLOWNESS;

    fun getAbility(): Ability {
        return when (this) {
            EXTRA_SPEED -> Ability(1.7f, 1f, null)
            SLOWNESS -> Ability(.4f, 1f, null)
        }
    }
}