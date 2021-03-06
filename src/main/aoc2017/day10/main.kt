package aoc2017.day10

data class Tie(val list: MutableList<Int>, var position: Int, var skip: Int) {
    fun copy() = Tie(list.toMutableList(), position, skip)
}

fun Tie.tying(length: Int): Tie {
    repeat(length / 2) { i ->
        val pos1 = (position + i) % list.size
        val pos2 = (position + length - 1 - i + list.size) % list.size

        val t = list[pos1]
        list[pos1] = list[pos2]
        list[pos2] = t
    }

    position += length + skip
    position %= list.size

    skip += 1

    return this
}

fun taskA(ringSize: Int, lengths: List<Int>): Int {
    val tie = Tie((0..ringSize - 1).toMutableList(), 0, 0)

    lengths.forEach { tie.tying(it) }

    return tie.list[0] * tie.list[1]
}

fun taskB(input: String): String {
    val tie = Tie((0..255).toMutableList(), 0, 0)

    val lengths = input.map(Char::toInt) + listOf(17, 31, 73, 47, 23)

    repeat(64) {
        lengths.forEach { length -> tie.tying(length) }
    }

    val denseHash = tie.list.chunked(16).map { block -> block.reduce(Int::xor) }

    return denseHash.joinToString(separator = "", transform = { "%02x".format(it) })
}
