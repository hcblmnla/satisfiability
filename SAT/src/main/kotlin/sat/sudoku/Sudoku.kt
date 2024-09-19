package sat.sudoku

/**
 * @author alnmlbch
 */
data class Sudoku(
    val field: List<List<Int>>,
) {
    init {
        assert(field.size == 9)
        field.forEach { row ->
            assert(row.size == 9)
        }
    }

    override fun toString(): String =
        field.joinToString(separator = "\n") { row ->
            row.joinToString("")
        }
}
