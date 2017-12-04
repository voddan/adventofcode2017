package aoc2017.day3

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals

class Test : Spek({
    val puzzlerInput = 289326

    given("1st task") {
        it("should produce 0 on 1") {
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

            val expect = -1
            it("should produce $expect") {
                assertEquals(expect , result)
            }
        }
    }

})
