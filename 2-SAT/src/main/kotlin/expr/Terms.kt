package expr

val x = Var("x")
val y = Var("y")
val z = Var("z")

data class Var(
    val name: String,
) {
    val neg get() = Var("!$name")
    infix fun or(other: Var) = Disjunction(this, other)
}

data class Disjunction(
    val left: Var,
    val right: Var,
) {
    override fun toString() = "($left ∨ $right)"
}

data class Cnf2(
    val disjunctions: List<Disjunction>,
) {
    constructor(vararg disjunctions: Disjunction) : this(disjunctions.toList())
}
