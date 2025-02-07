package baekjoon.prefixsum

import kotlin.math.absoluteValue

class `15980` {
    fun main() {
        val (N, M) = readln().split(" ").map(String::toInt)
        val B = List(N) { readln().split(" ") }
        val dNum = mapOf("L" to -1, "R" to 1)
        val P = IntArray(M).apply {
            for ((d, s) in B) {
                s.forEachIndexed { j, c -> this[j] += (dNum[d]!!.takeIf { c == '1' } ?: 0) }
            }
            for (i in 1 until size) {
                this[i] += this[i - 1]
            }
        }
        val eachPrefix = List(N) { i ->
            IntArray(M).apply {
                val (d, s) = B[i]
                s.forEachIndexed { j, c -> this[j] += (dNum[d]!!.takeIf { c == '1' } ?: 0) }
                (1 until size).forEach { this[it] += this[it - 1] }
            }
        }
        var answerIdx = 0
        var answer = Int.MAX_VALUE
        for (i in B.indices) {
            var temp = 0
            B[i][1].indices.forEach {
                temp = maxOf(temp, (P[it] - eachPrefix[i][it]).absoluteValue)
            }

            if (temp < answer) {
                answerIdx = i
                answer = temp
            }
        }
        print("${answerIdx + 1}\n$answer")
    }
}