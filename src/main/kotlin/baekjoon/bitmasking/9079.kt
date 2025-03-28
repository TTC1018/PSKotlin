package baekjoon.bitmasking

import kotlin.math.pow

class `9079` {
    private val MAX = (2.0).pow(9).minus(1).toInt()
    private val s = listOf(
        Triple(0, 1, 2), Triple(3, 4, 5), Triple(6, 7, 8),
        Triple(0, 4, 8), Triple(2, 4, 6),
        Triple(0, 3, 6), Triple(1, 4, 7), Triple(2, 5, 8)
    )

    fun solution() {
        print(
            StringBuilder().apply {
                var T = readln().toInt()
                val V = mutableSetOf<Int>()
                var nows = mutableListOf<Int>()
                val nexts = mutableListOf<Int>()
                loop@ while (T-- > 0) {
                    V.clear()
                    nows.clear()
                    nexts.clear()
                    List(3) { readln().split(" ") }.run {
                        var result = 0
                        for (i in 0..2) {
                            for (j in 0..2) {
                                if (this[i][j] == "H") {
                                    result = result or (1 shl (i * 3 + j))
                                }
                            }
                        }
                        V += result
                        nows += result
                    }
                    var cnt = 0
                    while (nows.isNotEmpty()) {
                        for (now in nows) {
                            if (now == 0 || now == MAX) {
                                appendLine(cnt)
                                continue@loop
                            }

                            s.forEach { (b1, b2, b3) ->
                                val next = now.xor(1 shl b1).xor(1 shl b2).xor(1 shl b3)
                                if (next !in V) {
                                    nexts += next
                                    V += next
                                }
                            }
                        }
                        cnt++
                        nows.clear()
                        nows.addAll(nexts)
                        nexts.clear()
                    }
                    appendLine(-1)
                }
            }.dropLast(1)
        )
    }
}