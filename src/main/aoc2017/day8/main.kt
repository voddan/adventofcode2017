package aoc2017.day8

import kotlin.math.max


enum class ComparisonOperator(val repr: String, val cmp: (reg: Int, num: Int) -> Boolean) {
    EQ("==", { reg, num -> reg == num}),
    NQ("!=", { reg, num -> reg != num}),
    LE("<=", { reg, num -> reg <= num}),
    GE(">=", { reg, num -> reg >= num}),
    LT("<" , { reg, num -> reg <  num}),
    GT(">" , { reg, num -> reg >  num});

    companion object {
        fun parse(str: String) = values().first { it.repr == str }
    }
}

data class Instruction(val reg: String,
                       val inc: Boolean,
                       val num: Int,
                       val condReg: String,
                       val condCmp: ComparisonOperator,
                       val condNum: Int) {

    fun run(registers: MutableMap<String, Int>) {
        val cond = registers.getOrDefault(condReg, 0)
        if(!condCmp.cmp(cond, condNum)) return

        val old = registers.getOrDefault(reg, 0)
        val new = old + if(inc) num else -num
        registers[reg] = new
    }

    companion object {
        // b inc 5 if a > 1
        private val re = Regex("""(\w+) (\w{3}) (-?\d+) if (\w+) (\S{1,2}) (-?\d+)""")

        fun parse(str: String): Instruction {
            val groups = re.matchEntire(str)?.groupValues ?: throw RuntimeException("$str did not match :(")
            return Instruction(groups[1], groups[2] == "inc", groups[3].toInt(),
                    groups[4], ComparisonOperator.parse(groups[5]), groups[6].toInt())
        }
    }
}

fun taskA(input: List<String>): Int {
    val instructions = input.map { Instruction.parse(it) }
    val registers = mutableMapOf<String, Int>()
    instructions.forEach { it.run(registers) }

    return registers.values.max() ?: throw RuntimeException("no registers found :(")
}

fun taskB(input: List<String>): Int {
    val instructions = input.map { Instruction.parse(it) }
    val registers = mutableMapOf<String, Int>()

    var maxValue = Int.MIN_VALUE
    instructions.forEach {
        it.run(registers)
        maxValue = max(maxValue, registers.values.max() ?: Int.MIN_VALUE)
    }

    return maxValue
}
