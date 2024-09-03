package baekjoon.dp

import kotlin.math.absoluteValue

class `1788` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        // F(n - 2) = F(n) - F(n - 1)
        val F = buildMap<Int, Int> {
            put(0, 0)
            put(1, 1)
            if (n >= 2) {
                for (i in 2..n) {
                    put(i, (this[i - 1]!! + this[i - 2]!!) % 1e9.toInt())
                }
            }
            if (n < 0) {
                for (i in -1 downTo n) {
                    put(i, (this[i + 2]!! - this[i + 1]!!) % 1e9.toInt())
                }
            }
        }
        when {
            F[n]!! > 0 -> println(1)
            F[n]!! == 0 -> println(0)
            F[n]!! < 0 -> println(-1)
        }
        print(F[n]!!.absoluteValue)
    }
}