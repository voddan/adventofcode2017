package day1

fun taskA(input: String): Int {
    val digits = input.map { (it - '0').toInt() }
    val pairs = (digits + digits.first()).zipWithNext()
    val sum = pairs.filter { it.first == it.second }.sumBy { it.first }
    return sum
}

fun taskB(input: String): Int {
    val inputSize = input.length
    require(inputSize % 2 == 0) { "Input must have an even number of elements, but has $inputSize" }
    val step = inputSize / 2

    val digits = input.map { (it - '0').toInt() }
    val ring = digits + digits
    val pairedDigits = digits.filterIndexed { i, d -> d == ring[i + step] }
    return pairedDigits.sum()
}
