package baekjoon.mos

import kotlin.math.sqrt

class `13704` {
    fun solution() {
        val (N, K) = readln().split(" ").map(String::toInt)
        val A = readln().split(" ").map(String::toInt).let {
            // prefix xor 배열 선언
            IntArray(N + 1).apply {
                this[1] = it[0]
                for (i in 2..N) {
                    this[i] = it[i - 1]
                    this[i] = this[i] xor this[i - 1]
                }
            }
        }
        val sqrtN = sqrt(N.toDouble()).toInt()
        val M = readln().toInt()
        val Q = List(M) { i -> readln().split(" ").map(String::toInt) + i }
            .sortedWith(compareBy({ it[0] / sqrtN }, { it[1] }))
            .map { Triple(it[0] - 1, it[1], it[2]) }
        val answer = LongArray(M)
        var answerVal = 0L
        val counter = IntArray(1 shl 20).apply {
            val (s, e, i) = Q.first()
            (s..e).forEach {
                answerVal += this[K xor A[it]]
                this[A[it]]++
            }
            answer[i] = answerVal
        }
        // A[j] xor A[i - 1] = [i, j] xor 값
        // A[j] xor A[i - 1] = K
        // A[j] = A[i - 1] xor K
        for (i in 1 until M) {
            val (ps, pe, _) = Q[i - 1]
            val (s, e, qi) = Q[i]
            (s until ps).forEach {
                answerVal += counter[K xor A[it]]
                counter[A[it]]++
            }
            (ps until s).forEach {
                counter[A[it]]--
                answerVal -= counter[K xor A[it]]
            }
            (e + 1..pe).forEach {
                counter[A[it]]--
                answerVal -= counter[K xor A[it]]
            }
            (pe + 1..e).forEach {
                answerVal += counter[K xor A[it]]
                counter[A[it]]++
            }
            answer[qi] = answerVal
        }
        print(answer.joinToString("\n"))
    }
}