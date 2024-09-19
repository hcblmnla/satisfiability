package sat.sudoku

import org.kosat.Kosat
import sat.SatSolver

/**
 * @author alnmlbch
 */
class SudokuSatSolver : SatSolver<Sudoku, Sudoku> {

    private fun vn(x: Int, y: Int, value: Int) = 9 * 9 * x + 9 * y + value

    override fun solve(input: Sudoku): Sudoku? {
        val solver = Kosat(mutableListOf(), 9 * 81)
        repeat(9) { x ->
            repeat(9) { y ->
                solver.addClause((1..9).map { c ->
                    vn(x, y, c)
                })
                (1..9).forEach { c1 ->
                    (c1 + 1..9).forEach { c2 ->
                        solver.addClause(-vn(x, y, c1), -vn(x, y, c2))
                    }
                }
            }
        }
        fun removeNeighbors(x: Int, y: Int, c: Int) {
            val vn = vn(x, y, c)
            (0 until x).union(x + 1 until 9).forEach { x1 ->
                solver.addClause(-vn(x1, y, c), -vn)
            }
            (0 until y).union(y + 1 until 9).forEach { y1 ->
                solver.addClause(-vn(x, y1, c), -vn)
            }
            val bx = (x / 3) * 3
            val by = (y / 3) * 3
            (0 until 3).forEach { dx ->
                (0 until 3).forEach { dy ->
                    val newX = bx + dx
                    val newY = by + dy
                    if (newX != x && newY != y) {
                        solver.addClause(-vn(newX, newY, c), -vn)
                    }
                }
            }
        }
        repeat(9) { x ->
            repeat(9) { y ->
                (1..9).forEach { c ->
                    removeNeighbors(x, y, c)
                }
            }
        }
        val assumptions = (0 until 9).flatMap { x ->
            (0 until 9).filter { y ->
                input.field[x][y] != 0
            }.map { y ->
                vn(x, y, input.field[x][y])
            }
        }
            .toMutableList()
        val status = solver.solve(assumptions)
        if (!status)
            return null
        val result = MutableList(9) { MutableList(9) { 0 } }
        repeat(9) { x ->
            repeat(9) { y ->
                val color = (1..9).first { c ->
                    val varNum = vn(x, y, c)
                    solver.getValue(varNum)
                }
                result[x][y] = color
            }
        }
        return Sudoku(result)
    }
}
