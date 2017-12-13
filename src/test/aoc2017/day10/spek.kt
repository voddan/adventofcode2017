package aoc2017.day10

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals

val puzzlerInput = "199,0,255,136,174,254,227,16,51,85,1,2,22,17,7,192"

class TestA : Spek({

    given("a ring of 5") {
        val tie5 = Tie((0..4).toMutableList(), 0, 0)

        on("tying [0, 1, 2, 3, 4] for 1st time with length 3") {
            val tie = tie5.copy().tying(3)

            it("positions to 3") {
                assertEquals(3, tie.position)
            }
            it("'s skip is 1") {
                assertEquals(1, tie.skip)
            }
            it("'s list is (2 1 0 3 4)") {
                assertEquals(listOf(2, 1, 0, 3, 4), tie.list)
            }
        }

        on("tying [0, 1, 2, 3, 4] for 2nd time with length 4") {
            val tie = tie5.copy().tying(3).tying(4)

            it("positions to 3") {
                assertEquals(3, tie.position)
            }
            it("'s skip is 2") {
                assertEquals(2, tie.skip)
            }
            it("'s list is (4 3 0 1 2)") {
                assertEquals(listOf(4, 3, 0, 1, 2), tie.list)
            }
        }

        on("tying [0, 1, 2, 3, 4] for 3rd time with length 1") {
            val tie = tie5.copy().tying(3).tying(4).tying(1)

            it("positions to 1") {
                assertEquals(1, tie.position)
            }
            it("'s skip is 3") {
                assertEquals(3, tie.skip)
            }
            it("'s list is (4 3 0 1 2)") {
                assertEquals(listOf(4, 3, 0, 1, 2), tie.list)
            }
        }

        on("tying [0, 1, 2, 3, 4] for 4th time with length 5") {
            val tie = tie5.copy().tying(3).tying(4).tying(1).tying(5)

            it("positions to 4") {
                assertEquals(4, tie.position)
            }
            it("'s skip is 4") {
                assertEquals(4, tie.skip)
            }
            it("'s list is (3 4 2 1 0)") {
                assertEquals(listOf(3, 4, 2, 1, 0), tie.list)
            }
        }

        on("tying [0.255] with lengths [2]") {
            val tie = Tie((0..255).toMutableList(), 0, 0).tying(2)
            it("returns [1, 0, 2 .. 255]") {
                assertEquals(listOf(1, 0) + (2..255), tie.list)
            }
        }

        on("calculation the result of multiplying the first two numbers") {
            it("returns 12 on input of [3, 4, 1, 5] on a ring of 5") {
                assertEquals(12, taskA(5, listOf(3, 4, 1, 5)))
            }

            it("returns 2 on input of [3] on a ring of 5") {
                assertEquals(2, taskA(5, listOf(3)))
            }

            it("returns 12 on input of [3, 4] on a ring of 5") {
                assertEquals(12, taskA(5, listOf(3, 4)))
            }

            it("returns 0 on input of [2, 4] on a ring of 256") {
                assertEquals(0, taskA(256, listOf(2, 4)))
            }
        }

        on("the puzzler's input $puzzlerInput") {
            val result = taskA(256, puzzlerInput.split(",").map(String::toInt))

            it("produces $result") {}

            val expect = 3770
            it("should produce $expect") {
                assertEquals(expect, result)
            }
        }
    }
})

class TestB : Spek({
    on("calculating the Knot Hash") {
        it("returns a2582a3a0e66e6e86e3812dcb672a272 for an empty string") {
            assertEquals("a2582a3a0e66e6e86e3812dcb672a272", taskB(""))
        }

        it("returns 33efeb34ea91902bb2f59c9920caa6cd for 'AoC 2017'") {
            assertEquals("33efeb34ea91902bb2f59c9920caa6cd", taskB("AoC 2017"))
        }

        it("returns 3efbe78a8d82f29979031a4aa0b16a9d for '1,2,3'") {
            assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", taskB("1,2,3"))
        }

        it("returns 63960835bcdc130f0b66d7ff4f6a5a8e for '1,2,4'") {
            assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", taskB("1,2,4"))
        }
    }

    on("the puzzler's input $puzzlerInput") {
        val result = taskB(puzzlerInput)

        it("produces $result") {}

        val expect = "a9d0e68649d0174c8756a59ba21d4dc6"
        it("should produce $expect") {
            assertEquals(expect, result)
        }
    }
})

