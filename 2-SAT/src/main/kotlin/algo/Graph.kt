package algo

/**
 * @author alnmlbch
 */
interface Graph {
    val size: Int
    fun add(v: Int, u: Int)
    fun transposed(): Graph
    fun topSort(): List<Int>
    fun component(): IntArray
}

/**
 * @author alnmlbch
 */
data class AdjGraph(
    val adj: List<ArrayList<Int>>,
) : Graph {
    constructor(n: Int) : this(List(n) { ArrayList<Int>() })

    operator fun get(i: Int) = adj[i]
    operator fun get(i: Int, j: Int) = adj[i][j]

    override val size: Int get() = adj.size

    override fun add(v: Int, u: Int) {
        adj[v].add(u)
    }

    override fun transposed(): AdjGraph {
        val res = AdjGraph(size)
        repeat(size) { v ->
            for (u in adj[v]) {
                res.add(u, v)
            }
        }
        return res
    }

    override fun topSort(): List<Int> {
        val used = BooleanArray(size) { false }
        val order = ArrayList<Int>()
        fun dfs(v: Int) {
            used[v] = true
            for (u in adj[v]) {
                if (!used[u]) {
                    dfs(u)
                }
            }
            order.add(v)
        }
        repeat(size) { v ->
            if (!used[v]) {
                dfs(v)
            }
        }
        return order.reversed()
    }

    override fun component(): IntArray {
        var components = 0
        val component = IntArray(size) { 0 }
        fun dfs(v: Int) {
            component[v] = components
            for (u in adj[v]) {
                if (component[u] == 0) {
                    dfs(u)
                }
            }
        }
        for (v in topSort()) {
            if (component[v] == 0) {
                components++
                dfs(v)
            }
        }
        return component
    }
}
