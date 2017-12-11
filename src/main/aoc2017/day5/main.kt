package aoc2017.day5


fun taskA(input: List<Int>): Int {
    val arr = ArrayList(input)

    var pos = 0
    var count = 0

    while(pos in arr.indices) {
        count++

        val next = arr[pos]
        arr[pos] += 1
        pos += next
    }

    return count
}


fun taskB(input: List<Int>): Int {
    val arr = ArrayList(input)

    var pos = 0
    var count = 0

    while(pos in arr.indices) {
        count++

        val offset = arr[pos]
        arr[pos] += if(offset < 3) 1 else -1
        pos += offset
    }

    return count
}


fun main(args: Array<String>) {
    taskA(listOf(0, 3, 0, 1, -3))
}
