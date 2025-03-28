package baekjoon.mos

import kotlin.math.sqrt

class `13028` {
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

    fun solution() {
        val (n, q) = readln().split(" ").map(String::toInt).also { Query.nSqrt = sqrt(it[0].toDouble()).toInt() }
        val A = readln().split(" ").map(String::toInt)
        val Q = List(q) { i -> readln().split(" ").map { it.toInt() - 1 }.let { Query(it[0], it[1], i) } }
            .sorted()
        val answer = IntArray(q)
        var moreThan3 = 0
        val counter = IntArray(1e5.toInt() + 1).apply {
            Q.first().let { (s, e, i) ->
                (s..e).forEach {
                    if (++this[A[it]] == 3) {
                        moreThan3++
                    }
                }
                answer[i] = moreThan3
            }
        }
        for (j in 1 until q) {
            val (pa, pb) = Q[j - 1]
            val (a, b, i) = Q[j]
            // a가 더 작은 범위인 경우 -> 추가
            (a until pa).forEach {
                if (++counter[A[it]] == 3) {
                    moreThan3++
                }
            }
            // b가 더 큰 범위인 경우 -> 추가
            (pb + 1..b).forEach {
                if (++counter[A[it]] == 3) {
                    moreThan3++
                }
            }
            // b가 더 작은 범위인 경우 -> 제거
            (b + 1..pb).forEach {
                if (--counter[A[it]] == 2) {
                    moreThan3--
                }
            }
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            (pa until a).forEach {
                if (--counter[A[it]] == 2) {
                    moreThan3--
                }
            }
            answer[i] = moreThan3
        }
        print(answer.joinToString("\n"))
    }
}