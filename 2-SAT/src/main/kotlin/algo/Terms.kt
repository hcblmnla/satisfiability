package algo

val x = Var("x")
val y = Var("y")
val z = Var("z")

@Suppress("unused")
private val example = Cnf2(
    x or y,
    x.ne or z,
    z.ne or y.ne,
)

/**
 * @author alnmlbch
 */
data class Var(
    val name: String,
) {
    val ne get() = Var("!$name")
    infix fun or(other: Var) = Disjunction(this, other)
}

/**
 * @author alnmlbch
 */
data class Disjunction(
    val left: Var,
    val right: Var,
) {
    override fun toString() = "($left ∨ $right)"
}

/**
 * @author alnmlbch
 */
data class Cnf2(
    val disjunctions: List<Disjunction>,
) {
    constructor(vararg disjunctions: Disjunction) : this(disjunctions.toList())
}
