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
