package algo

/**
 * @author alnmlbch
 */
data class Colors(
    val colors: List<Int>,
) {
    operator fun get(i: Int) = colors[i]
    val size: Int get() = colors.size
}
