package cloud.ggomes.playground

enum class ButtonState(val value: Int) {
    FILLED(0), OUTLINED(1);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): ButtonState {
            return when (value) {
                0 -> FILLED
                1 -> OUTLINED
                else -> throw IllegalArgumentException("Invalid value for ButtonState")
            }
        }
    }
}
