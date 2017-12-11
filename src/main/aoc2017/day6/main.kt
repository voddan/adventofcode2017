package aoc2017.day6


fun redistribute(banks: List<Int>): List<Int> {
    val maxId = banks.indexOf(banks.max())
    val arr = banks.toMutableList()
    val size = arr.size

    val blocks = arr[maxId]
    arr[maxId] = 0

    repeat(blocks) { i ->
        arr[(i + maxId + 1) % size] += 1
    }

    return arr
}

fun taskA(input: List<Int>): Int {
    val history = mutableSetOf<List<Int>>()

    var banks = input.toList()
    do {
        history += banks
        banks = redistribute(banks)
    } while(banks !in history)

    return history.size
}


fun main(args: Array<String>) {
    taskA(listOf(0, 2, 7, 0))
}
