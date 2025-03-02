package baekjoon.mos

import kotlin.math.sqrt

class `12896` {
    private data class Query(
        val s: Int,
        val e: Int,
        val i: Int,
    ) : Comparable<Query> {
        override fun compareTo(other: Query): Int {
            val s1 = s / nSqrt
            val otherS1 = other.s / nSqrt
            if (s1 == otherS1) return e.compareTo(other.e)
            return s1.compareTo(otherS1)
        }

        companion object {
            var nSqrt = 0
        }
    }

    private val BASE = 1e5.toInt()

    fun solution() {
        val (n, q) = readln().split(" ").map(String::toInt).also { Query.nSqrt = sqrt(it[0].toDouble()).toInt() }
        val A = readln().split(" ").map { BASE + it.toInt() }
        val Q = List(q) { i -> readln().split(" ").map { it.toInt() - 1 }.let { Query(it[0], it[1], i) } }
            .sorted()
        val answer = IntArray(q)
        var maxVal = 0
        // counter[x]의 개수를 기록할 변수
        // 만약 memo[counter[x]]가 새롭게 1이 되거나, 0이 되면 최대 등장 수가 변해야 되는 시점이다
        // 새로운 counter[x]개가 등장했거나, 기존의 counter[x]개가 줄어드는 시점이기 때문
        val memo = IntArray(BASE * 2 + 1)
        val counter = IntArray(BASE * 2 + 1).apply {
            Q.first().let { (s, e, i) ->
                (s..e).forEach {
                    memo[this[A[it]]]--
                    ++memo[++this[A[it]]]
                    maxVal = maxVal.coerceAtLeast(this[A[it]])
                }
                answer[i] = maxVal
            }
        }
        for (j in 1 until q) {
            val (pa, pb) = Q[j - 1]
            val (a, b, i) = Q[j]
            // a가 더 작은 범위인 경우 -> 추가
            (a until pa).forEach {
                memo[counter[A[it]]]--
                ++memo[++counter[A[it]]]
                maxVal = maxVal.coerceAtLeast(counter[A[it]])
            }
            // b가 더 큰 범위인 경우 -> 추가
            (pb + 1..b).forEach {
                memo[counter[A[it]]]--
                ++memo[++counter[A[it]]]
                maxVal = maxVal.coerceAtLeast(counter[A[it]])
            }
            // b가 더 작은 범위인 경우 -> 제거
            (b + 1..pb).forEach {
                if (--memo[counter[A[it]]] == 0 && maxVal == counter[A[it]]) maxVal--
                memo[--counter[A[it]]]++
            }
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            (pa until a).forEach {
                if (--memo[counter[A[it]]] == 0 && maxVal == counter[A[it]]) maxVal--
                memo[--counter[A[it]]]++
            }
            answer[i] = maxVal
        }
        print(answer.joinToString("\n"))
    }
}