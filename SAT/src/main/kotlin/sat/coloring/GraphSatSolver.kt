package sat.coloring

import algo.AdjGraph
import algo.Colors
import org.kosat.Kosat
import sat.SatSolver

/**
 * @author alnmlbch
 */
@Suppress("Unused") // TODO: test
class GraphSatSolver : SatSolver<AdjGraph, Colors> {

    private fun vn(u: Int, c: Int) = 3 * u + c + 1

    override fun solve(input: AdjGraph): Colors? {
        val solver = Kosat(mutableListOf(), 3 * input.size)
        val range = (0 until input.size)
        range.forEach { u ->
            val c1 = vn(u, 0)
            val c2 = vn(u, 1)
            val c3 = vn(u, 2)
            solver.addClause(c1, c2, c3)
            solver.addClause(-c1, -c2)
            solver.addClause(-c2, -c3)
            solver.addClause(-c1, -c3)
        }
        for (u in range)
            for (v in input[u])
                for (c in 0 until 3)
                    solver.addClause(-vn(u, c), -vn(v, c))
        val sat = solver.solve()
        if (!sat)
            return null
        val colors = ArrayList<Int>()
        for (u in range) {
            val color = (0 until 3).first {
                val c = vn(u, it)
                solver.getValue(c)
            }
            colors.add(color)
        }
        return Colors(colors)
    }
}
