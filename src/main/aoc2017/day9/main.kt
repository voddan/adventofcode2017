package aoc2017.day9


sealed class Region(val start: Char, val score: Int, val canClose: (Char) -> Boolean)
class Group(score: Int) : Region('{', score, { it == '}' })
class Garbage() : Region('<', 0, { it == '>' })
class Exclamation() : Region('!', 0, { true })

/**
 * @return: unrolled stack tokens. The most outermost is the last
 * */
fun parse(str: String): List<Region> {
    return listOf<Region>()
}

fun taskA(input: String): Int {
    return 0
}
