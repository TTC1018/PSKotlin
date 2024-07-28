package baekjoon.prefixsum

class `13422` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val (N, M, K) = readLine().split(" ").map(String::toInt)
            val H = readLine().split(" ").map(String::toInt).run { this + slice(0 until M - 1) }
            if (N == M) {
                if (H.slice(0 until N).sumOf { it } < K) {
                    sb.appendLine(1)
                } else {
                    sb.appendLine(0)
                }
            } else {
                val P = IntArray(H.size).apply {
                    this[0] = H.first()
                    for (i in 1 until H.size) {
                        this[i] += this[i - 1] + H[i]
                    }
                }
                var answer = 0
                for (i in M - 1 until H.size) {
                    if (P[i] - P.getOrElse(i - M) { 0 } < K)
                        answer++
                }
                sb.appendLine(answer)
            }
        }
        print(sb.dropLast(1))
    }
}