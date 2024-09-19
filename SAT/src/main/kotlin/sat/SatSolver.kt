package sat

/**
 * Simple SAT solver.
 *
 * @author alnmlbch
 */
fun interface SatSolver<I, O> {

    /**
     * Solves the [input].
     *
     * @return the solution if there is one, otherwise `null`.
     */
    fun solve(input: I): O?
}
