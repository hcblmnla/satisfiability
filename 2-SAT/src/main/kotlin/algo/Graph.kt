package algo

/**
 * @author alnmlbch
 */
data class AdjGraph(
    val adj: List<List<Int>>,
) {
    operator fun get(i: Int) = adj[i]
    operator fun get(i: Int, j: Int) = adj[i][j]
    val size: Int get() = adj.size
}

/**
 * @author alnmlbch
 */
data class Colors(
    val colors: List<Int>,
) {
    operator fun get(i: Int) = colors[i]
    val size: Int get() = colors.size
}

data class Edge(
    val v: Int,
    val u: Int,
)
