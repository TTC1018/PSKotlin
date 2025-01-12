package baekjoon.math

class `9333` {
    fun solution() = with(System.`in`.bufferedReader()) {
        print(
            StringBuilder().apply {
                var T = readLine().toInt()
                loop@ while (T-- > 0) {
                    val (R, B, M) = readLine().trim().split(" ")
                        .map { it.split(".").joinToString("").toLong() }

                    var total = B
                    val r = 1e4.toLong() + R
                    for (cycle in 1..1200) {
                        val next = total.times(r).div(100).run {
                            div(100) + ((1).takeIf { mod(100) >= 50 } ?: 0) - M
                        }

                        if (next >= total) break
                        else if (next <= 0) {
                            appendLine(cycle)
                            continue@loop
                        }
                        total = next
                    }
                    appendLine("impossible")
                }
            }.dropLast(1)
        )
    }
}