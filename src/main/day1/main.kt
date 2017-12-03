package day1

fun taskA(input: String): Int {
    val digits = input.map { (it - '0').toInt() }
    val pairs = (digits + digits.first()).zipWithNext()
    val sum = pairs.filter { it.first == it.second }.sumBy { it.first }
    return sum
}

fun main(args: Array<String>) {
    taskA("1122")
}

