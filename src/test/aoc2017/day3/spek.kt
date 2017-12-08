package aoc2017.day3

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals

class Test : Spek({
    val puzzlerInput = 289326

    given("1st task") {
        xit("should produce 0 on 1") {
            assertEquals(0, taskA(1))
        }

        it("should produce 3 on 12") {
            assertEquals(3, taskA(12))
        }

        it("should produce 2 on 23") {
            assertEquals(2, taskA(23))
        }

        it("should produce 31 on 1024") {
            assertEquals(31, taskA(1024))
        }

        on("the puzzler's input of $puzzlerInput") {
            val result = taskA(puzzlerInput)

            it("produces $result") {}

            val expect = 419
            it("should produce $expect") {
                assertEquals(expect , result)
            }
        }

        on("the round of 3") {
            checkTask(2, 1, ::taskA)
            checkTask(5, 2, ::taskA)
            checkTask(6, 1, ::taskA)
            checkTask(8, 1, ::taskA)
            checkTask(9, 2, ::taskA)
        }

        on("the round of 5") {
            checkTask(10, 3, ::taskA)
            checkTask(11, 2, ::taskA)
            checkTask(12, 3, ::taskA)
            checkTask(13, 4, ::taskA)
            checkTask(14, 3, ::taskA)

            checkTask(24, 3, ::taskA)
            checkTask(25, 4, ::taskA)
            checkTask(26, 5, ::taskA)
            checkTask(27, 4, ::taskA)
        }
    }

    given("2 task") {
        on("requesting squares in order") {
            val expect = listOf(1, 1, 2, 4, 5, 10, 11, 23, 25, 26, 54, 57, 59, 122, 133, 142, 147, 304, 330, 351, 362, 747, 806)
            val result = squaresInOrder().take(expect.size).toList()

            for(i in expect.indices) {
                it("correctly calculates the $i'th square") {
                    assertEquals(expect[i], result[i])
                }
            }
        }

        on("calculating the first value that is larger than your puzzle input") {
            it("returns 59 for 57") {
                assertEquals(59, taskB(57))
            }

            it("returns 142 for 134") {
                assertEquals(142, taskB(134))
            }
        }

        on("the puzzler's input") {
            val result = taskB(puzzlerInput)

            it("produces $result") {}

            val expect = -1
            it("should produce $expect") {
                assertEquals(expect , result)
            }
        }
    }
})

fun TestContainer.checkTask(input: Int, expect: Int, action: (Int) -> Int) {
    it("would better return $expect when given $input") {
        assertEquals(expect, action(input))
    }
}