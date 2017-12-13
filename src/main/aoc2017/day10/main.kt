package aoc2017.day10

data class Tie(val list: MutableList<Int>, var position: Int, var skip: Int)

fun Tie.tying(length: Int): Tie {
    return this
}

fun taskA(ringSize: Int, lengths: List<Int>): Int {
    return 0
}
