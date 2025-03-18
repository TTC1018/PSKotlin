package baekjoon.mos

import java.util.StringTokenizer
import kotlin.math.sqrt

class `2912` {
    data class Query(
        val a: Int,
        val b: Int,
        val i: Int,
    ) : Comparable<Query> {
        override fun compareTo(other: Query): Int {
            val _a = a / sqrtN
            val _oa = other.a / sqrtN
            if (_a == _oa) return b.compareTo(other.b)
            return _a.compareTo(_oa)
        }

        companion object {
            var sqrtN = 0
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, C) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
        Query.sqrtN = sqrt(N.toDouble()).toInt()
        val S = readLine().split(" ").map(String::toInt)
        val M = readLine().toInt()
        val answer = IntArray(M)
        val Q = List(M) { i ->
            readLine().split(" ")
                .map(String::toInt)
                .let { Query(it[0] - 1, it[1] - 1, i) }
        }.sorted()
        val counter = IntArray(C + 1).apply {
            val (a, b, i) = Q.first()
            val half = (b - a + 1).floorDiv(2)
            for (n in a..b) {
                this[S[n]]++
            }
            for (n in indices) {
                if (this[n] > half) {
                    answer[i] = n
                    break
                }
            }
        }
        for (idx in 1 until M) {
            val (pa, pb, _) = Q[idx - 1]
            val (a, b, i) = Q[idx]
            val half = (b - a + 1).floorDiv(2)
            // a가 더 작은 범위인 경우 -> 추가
            for (n in a until pa) {
                counter[S[n]]++
            }
            // b가 더 큰 범위인 경우 -> 추가
            for (n in pb + 1..b) {
                counter[S[n]]++
            }
            // b가 더 작은 범위인 경우 -> 제거
            for (n in b + 1..pb) {
                counter[S[n]]--
            }
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            for (n in pa until a) {
                counter[S[n]]--
            }
            for (n in counter.indices) {
                if (counter[n] > half) {
                    answer[i] = n
                    break
                }
            }
        }
        print(
            StringBuilder().apply {
                answer.forEach {
                    if (it == 0) {
                        appendLine("no")
                    } else {
                        appendLine("yes $it")
                    }
                }
            }.dropLast(1)
        )
    }
}