package sat.sudoku

class SudokuChecker {

    /**
     * Checks that [solved] completes [sudoku].
     */
    fun check(sudoku: Sudoku, solved: Sudoku): Boolean =
        checkIsComplete(solved) && checkIsSubSudoku(solved, sudoku)

    private fun checkIsComplete(sudoku: Sudoku): Boolean {
        val digitSet = (1..9).toSet()
        for (i in 0 until 9) {
            val row = sudoku.field[i].toSet()
            if (row != digitSet)
                return false
            val column = sudoku.field.map { it[i] }.toSet()
            if (column != digitSet)
                return false
        }
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val square = mutableSetOf<Int>()
                for (x in 0 until 3)
                    for (y in 0 until 3)
                        square += sudoku.field[3 * i + x][3 * j + y]
                if (square != digitSet)
                    return false
            }
        }
        return true
    }

    private fun checkIsSubSudoku(sudoku: Sudoku, subSudoku: Sudoku): Boolean {
        for (i in 0 until 9)
            for (j in 0 until 9)
                if (subSudoku.field[i][j] != 0 && subSudoku.field[i][j] != sudoku.field[i][j])
                    return false
        return true
    }
}
