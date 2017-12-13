package aoc2017.day9


sealed class Region(val start: Char, val score: Int, val canClose: (Char) -> Boolean) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Region) return false

        if (start != other.start) return false
        if (score != other.score) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + score
        return result
    }
}

object Root : Region(' ', 0, { false })
class Group(score: Int) : Region('{', score, { it == '}' })
class Garbage() : Region('<', 0, { it == '>' })
class Exclamation() : Region('!', 0, { true })

/**
 * @return: unrolled stack tokens. The most outermost is the last
 * */
fun parse(str: String): List<Region> {
    val stack = mutableListOf<Region>(Root)
    val sequence = mutableListOf<Region>()

    for(ch in str) {
        val head = stack.last()

        if(head.canClose.invoke(ch)) {
            val v = stack.removeAt(stack.lastIndex)
            sequence.add(v)
            continue
        }

        when(head) {
            is Root -> when(ch) {
                '{' -> stack.add(Group(head.score + 1))
                '<' -> stack.add(Garbage())
                '!' -> stack.add(Exclamation())
            }
            is Group -> when(ch) {
                '{' -> stack.add(Group(head.score + 1))
                '<' -> stack.add(Garbage())
                '!' -> stack.add(Exclamation())
            }
            is Garbage -> when(ch) {
                '!' -> stack.add(Exclamation())
            }
        }
    }

    return sequence
}

fun taskA(input: String) = parse(input).sumBy { it.score }
