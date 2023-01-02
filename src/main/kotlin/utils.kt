const val BACKGROUND_COLOR_OFFSET = 16777216

const val COLOR_BITS_NUMBER = 8

fun convertSeparateToWholeRGB(r: Int, g: Int, b: Int): Int =
    (r shl COLOR_BITS_NUMBER * 2) + (g shl COLOR_BITS_NUMBER) + b
