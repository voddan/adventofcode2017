package day2

fun taskA(input: String): Int {
    val table: List<List<Int>> = input.split("\n").map {
        it.split(Regex("\\s")).map { it.toInt() }
    }

    return table.sumBy { row -> row.max()!! - row.min()!! }
}

fun taskB(input: String): Int {
    val table: List<List<Int>> = input.split("\n").map {
        it.split(Regex("\\s")).map { it.toInt() }
    }

    return table.sumBy { row -> row.max()!! - row.min()!! }
}

fun main(args: Array<String>) {
    taskB("5 9 2 8\n" +
            "9 4 7 3\n" +
            "3 8 6 5")
}