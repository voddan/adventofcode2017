package aoc2017.day3

import kotlin.math.*

fun taskA(input: Int): Int {
    require(input > 0)
    val sqrt = sqrt(input.toDouble()).nextDown().toInt()
    val oddSqrt = if(sqrt > 0 && sqrt % 2 == 0) sqrt - 1 else sqrt

    val rimNumber = input - oddSqrt * oddSqrt
    val sideNumber = rimNumber % (oddSqrt + 1)
    val centerSideNum = (oddSqrt + 1) / 2

    val longitude = (oddSqrt - 1) / 2 + 1
    val latitude = abs(centerSideNum - sideNumber)
    return longitude + latitude
}

fun taskB(input: Int): Int {
    return 0;
}

fun squaresInOrder(): Sequence<Int> {
    return sequenceOf(1)
}

fun main(args: Array<String>) {
    println(taskA(2))
}
