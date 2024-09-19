package sat.sudoku

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SudokuSatSolverTest {

    private val solver = SudokuSatSolver()
    private val checker = SudokuChecker()

    private fun readSudokuListFromFile(fn: String): List<Sudoku> {
        val inputStream = requireNotNull(
            ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream("sudoku/$fn")
        )
        val rawList = inputStream
            .readAllBytes()
            .decodeToString()
            .split("\\s+".toRegex())
        val fields = rawList.map { rawSudoku ->
            rawSudoku.chunked(9).map { row ->
                row.map { it.digitToInt() }
            }
        }
        val sudokuList = fields.map { Sudoku(it) }
        return sudokuList
    }

    private fun checkAllSolvable(fn: String) {
        val sudokuList = readSudokuListFromFile(fn)
        sudokuList.forEachIndexed { idx, sudoku ->
            val solved = solver.solve(sudoku)
            assertNotNull(solved) {
                "Expected solution: ${idx + 1}"
            }
            val correct = checker.check(sudoku, solved!!)
            assertTrue(correct) {
                "Sudoku ${idx + 1} is not correct!"
            }
        }
    }

    private fun checkAllUnsolvable(@Suppress("SameParameterValue") fn: String) {
        val sudokuList = readSudokuListFromFile(fn)
        sudokuList.forEachIndexed { idx, sudoku ->
            val solved = solver.solve(sudoku)
            assertNull(solved) {
                "Expected unsolvable: ${idx + 1}"
            }
        }
    }

    @Test
    fun test01_easiestSudoku() {
        checkAllSolvable("easiest.txt")
    }

    @Test
    fun test02_easySudoku() {
        checkAllSolvable("easy.txt")
    }

    @Test
    fun test03_hardSudoku() {
        checkAllSolvable("hard.txt")
    }

    @Test
    fun test04_hardestSudoku() {
        checkAllSolvable("hardest.txt")
    }

    @Test
    fun test05_unsolvableSudoku() {
        checkAllUnsolvable("unsolvable.txt")
    }
}
