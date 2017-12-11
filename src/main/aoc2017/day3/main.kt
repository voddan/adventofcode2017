package aoc2017.day3

import kotlin.coroutines.experimental.buildSequence
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

fun taskB(input: Int) = squaresInOrder(input).first { it > input }

fun squaresInOrder(maxCount: Int): Sequence<Int> = buildSequence {
    val size = taskA(maxCount)
    val arr = List(2 * size) { IntArray(2 * size) }

    fun get(i: Int, j: Int) = arr[i + size][j + size]
    fun set(i: Int, j: Int, value: Int) { arr[i + size][j + size] = value }

    val neighbors = listOf(
            -1 to -1, 0 to -1, 1 to -1,
            -1 to  0,          1 to  0,
            -1 to  1, 0 to  1, 1 to  1)

    fun sum(i: Int, j: Int) = neighbors.sumBy { (a, b) -> get(i + a, j + b) }

    fun dump(s: Int) {
        for(i in -s..s) {
            for(j in -s..s)
                print(get(j, i))
            println()
        }
    }

    fun update(i: Int, j: Int): Int {
        val s = sum(i, j)
        set(i, j, s)
        return s
    }


    var level = 0
    set(0, 0, 1)
    yield(1)
    while(true) {
        level++
        val side = 2 * level + 1
        val shift = level - 1

        repeat(side - 1) { i ->
            yield(update(level, i - shift))
        }
        repeat(side - 1) { i ->
            yield(update(shift - i, level))
        }
        repeat(side - 1) { i ->
            yield(update(-level, shift - i))
        }
        repeat(side - 1) { i ->
            yield(update(i - shift, - level))
        }
    }


}

fun main(args: Array<String>) {
    val sio = squaresInOrder(100).take(100)

    for( s in sio)
        println(s)
}
