package aoc2017.day8


enum class ComparisonOperator(val repr: String, val cmp: (reg: Int, num: Int) -> Boolean) {
    EQ("==", { reg, num -> reg == num}),
    NQ("!=", { reg, num -> reg != num}),
    LE("<=", { reg, num -> reg <= num}),
    GE(">=", { reg, num -> reg >= num}),
    LT("<" , { reg, num -> reg <  num}),
    GT(">" , { reg, num -> reg >  num}),
}

data class Instruction(val reg: String,
                       val inc: Boolean,
                       val num: Int,
                       val condReg: String,
                       val condCmp: ComparisonOperator,
                       val condNum: Int) {

    fun run(regirsters: MutableMap<String, Int>) {}

    companion object {
        fun parse(str: String): Instruction {
            return Instruction("", false, 0, "", ComparisonOperator.EQ, 0)
        }
    }
}

fun taskA(input: List<String>): Int {
    return 0
}


fun main(args: Array<String>) {
}
