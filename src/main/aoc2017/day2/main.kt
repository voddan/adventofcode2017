package aoc2017.day2

import com.marcinmoskala.math.combinations

fun taskA(input: String): Int {
    val table: List<List<Int>> = input.split("\n").map {
        it.split(Regex("\\s")).map { it.toInt() }
    }

    return table.sumBy { row -> row.max()!! - row.min()!! }
}

fun taskB(input: String): Int {
    val table: List<List<Int>> = input.split("\n").map {
        it.split(Regex("\\s")).map { it.toInt() }
    }

    val sum = table.sumBy { raw ->
        val combinations: List<Pair<Int, Int>> = raw.toSet().combinations(2).map { it.max()!! to it.min()!! }
        val (a, b)  = combinations.first { (a, b) -> a % b == 0 }
        a / b
    }

    return sum
}
