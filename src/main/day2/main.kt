package day2

fun taskA(input: String): Int {
    val table: List<List<Int>> = input.split("\n").map {
        it.split(Regex("\\s")).map { it.toInt() }
    }

    return table.sumBy { row -> row.max()!! - row.min()!! }
}

fun main(args: Array<String>) {
    taskA("5 1 9 5\n" +
            "7 5 3\n" +
            "2 4 6 8")
}