package baekjoon.math

class `17128` {
    fun solution() {
        val (N, Q) = readln().split(" ").map(String::toInt)
        val A = readln().split(" ").map(String::toInt)
        val Qi = readln().split(" ").map { it.toInt() - 1 }
        val P = IntArray(N) { A[it] * A[(it + 1).mod(N)] * A[(it + 2).mod(N)] * A[(it + 3).mod(N)] }
        print(
            StringBuilder().apply {
                var sumVal = P.sumOf { it }
                for (qi in Qi) {
                    sumVal -= 2 * (P[qi] + P[(qi - 1).mod(N)] + P[(qi - 2).mod(N)] + P[(qi - 3).mod(N)])
                    (qi - 3..qi).forEach { it.mod(N).run { P[this] = -P[this] } }
                    appendLine(sumVal)
                }
            }.dropLast(1)
        )
    }
}