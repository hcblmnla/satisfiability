package algo

/**
 * 2-SAT task.
 *
 * @author alnmlbch
 */
fun main() {
    while (true) {
        val line = readlnOrNull() ?: break
        val (n, m) = line.split(' ').map { it.toInt() }

        fun neg(v: Int) = if (v >= n) v - n else v + n
        val adj = AdjGraph(2 * n)

        repeat(m) {
            var (i1, e1, i2, e2) = readln().split(' ').map { it.toInt() }
            if (e1 == 0)
                i1 = neg(i1)
            if (e2 == 0)
                i2 = neg(i2)

            adj.add(neg(i1), i2)
            adj.add(neg(i2), i1)
        }

        val component = adj
            .transposed()
            .component()

        val ans = BooleanArray(n)
        repeat(n) { v ->
            ans[v] = component[v] > component[neg(v)]
        }
        println(ans.map { if (it) '1' else '0' }.joinToString(""))
    }
}
