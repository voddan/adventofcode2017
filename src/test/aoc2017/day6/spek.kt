package aoc2017.day6

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals

class Test : Spek({
    val puzzlerInput = listOf(14, 0, 15, 12, 11, 11, 3, 5, 1, 6, 8, 4, 9, 1, 8, 4)

    given("1st task") {
        on("running a single step") {
            it("redistributes `0, 2, 7, 0` into `2, 4, 1, 2`") {
                assertEquals(listOf(2, 4, 1, 2), redistribute(listOf(0, 2, 7, 0)))
            }

            it("redistributes `2, 4, 1, 2` into `3, 1, 2, 3`") {
                assertEquals(listOf(3, 1, 2, 3), redistribute(listOf(2, 4, 1, 2)))
            }

            it("redistributes `3, 1, 2, 3` into `0, 2, 3, 4`") {
                assertEquals(listOf(0, 2, 3, 4), redistribute(listOf(3, 1, 2, 3)))
            }

            it("redistributes `0, 2, 3, 4` into `1, 3, 4, 1`") {
                assertEquals(listOf(1, 3, 4, 1), redistribute(listOf(0, 2, 3, 4)))
            }

            it("redistributes `1, 3, 4, 1` into `2, 4, 1, 2`") {
                assertEquals(listOf(2, 4, 1, 2), redistribute(listOf(1, 3, 4, 1)))
            }
        }

        on("running a full redistribution routine") {
            it("redistributes `0, 2, 7, 0` in 5 steps") {
                assertEquals(5, taskA(listOf(0, 2, 7, 0)))
            }
        }

        on("the puzzler's input of ${puzzlerInput.size} instructions") {
            val result = taskA(puzzlerInput)

            it("produces $result") {}

            val expect = 11137
            it("should produce $expect") {
                assertEquals(expect, result)
            }
        }
    }

    given("2st task") {
        it("the redistribution loop for `0, 2, 7, 0` has 4 steps") {
            assertEquals(4, taskB(listOf(0, 2, 7, 0)))
        }

        on("the puzzler's input of ${puzzlerInput.size} instructions") {
            val result = taskB(puzzlerInput)

            it("produces $result") {}

            val expect = -1
            it("should produce $expect") {
                assertEquals(expect, result)
            }
        }
    }
})
