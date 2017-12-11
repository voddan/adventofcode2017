package aoc2017.day4


fun isValid(str: String): Boolean {
    val words = str.split(' ')
    return words.size == words.toSet().size
}

fun taskA(input: List<String>) = input.count(::isValid)


fun main(args: Array<String>) {
    taskA(listOf("aa bb", "a b a c"))
}
