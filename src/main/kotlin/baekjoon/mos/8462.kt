package baekjoon.mos

import kotlin.math.sqrt

class `8462` {
    private data class Query(
        val l: Int,
        val r: Int,
        val i: Int,
    ) : Comparable<Query> {
        override fun compareTo(other: Query): Int {
            val s1 = l / nSqrt
            val otherS1 = other.l / nSqrt
            if (s1 == otherS1) return r.compareTo(other.r)
            return s1.compareTo(otherS1)
        }

        companion object {
            var nSqrt = 0
        }
    }

    fun solution() {
        val (n, t) = readln().split(" ").map(String::toInt).also { Query.nSqrt = sqrt(it[0].toDouble()).toInt() }
        val A = readln().split(" ").map(String::toInt)
        val Q = List(t) { i -> readln().split(" ").map { it.toInt() - 1 }.let { Query(it[0], it[1], i) } }
            .sorted()
        val answer = LongArray(t)
        var answerVal = 0L
        val counter = IntArray(1e6.toInt() + 1).apply {
            Q.first().let { (s, e, i) ->
                (s..e).forEach {
                    answerVal -= this[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
                    this[A[it]]++
                    answerVal += this[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
                }
                answer[i] = answerVal
            }
        }
        for (j in 1 until t) {
            val (pa, pb) = Q[j - 1]
            val (a, b, i) = Q[j]
            // a가 더 작은 범위인 경우 -> 추가
            (a until pa).forEach {
                answerVal -= counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
                counter[A[it]]++
                answerVal += counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
            }
            // b가 더 큰 범위인 경우 -> 추가
            (pb + 1..b).forEach {
                answerVal -= counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
                counter[A[it]]++
                answerVal += counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
            }
            // b가 더 작은 범위인 경우 -> 제거
            (b + 1..pb).forEach {
                answerVal -= counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
                counter[A[it]]--
                answerVal += counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
            }
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            (pa until a).forEach {
                answerVal -= counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
                counter[A[it]]--
                answerVal += counter[A[it]].let { cnt -> 1L * cnt * cnt * A[it] }
            }
            answer[i] = answerVal
        }
        print(answer.joinToString("\n"))
    }
}