package baekjoon.mos

import kotlin.math.sqrt

class `14413` {
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
        val (N, Q) = readLine().trim().split(" ").map(String::toInt)
        Query.sqrtN = sqrt(N.toDouble()).toInt()
        val S = readLine().trim().split(" ").map(String::toInt).let {
            IntArray(N).apply {
                var idx = 0
                val idxMap = mutableMapOf<Int, Int>()
                for (i in indices) {
                    val num = it[i]
                    idxMap[num]?.let { this[i] = it }
                        ?: run {
                            idxMap[num] = idx++
                            this[i] = idxMap[num]!!
                        }
                }
            }.toList()
        }
        var answerVal = 0
        val answer = IntArray(Q)
        val q = List(Q) { i ->
            readLine().trim().split(" ")
                .map(String::toInt)
                .let { Query(it[0] - 1, it[1] - 1, i) }
        }.sorted()
        val counter = IntArray(N).apply {
            val (a, b, i) = q.first()
            for (n in a..b) {
                this[S[n]]++
                when {
                    this[S[n]] == 3 -> answerVal--
                    this[S[n]] == 2 -> answerVal++
                }
            }
            answer[i] = answerVal
        }
        for (idx in 1 until Q) {
            val (pa, pb, _) = q[idx - 1]
            val (a, b, i) = q[idx]
            // a가 더 작은 범위인 경우 -> 추가
            for (n in a until pa) {
                counter[S[n]]++
                when {
                    counter[S[n]] == 3 -> answerVal--
                    counter[S[n]] == 2 -> answerVal++
                }
            }
            // b가 더 큰 범위인 경우 -> 추가
            for (n in pb + 1..b) {
                counter[S[n]]++
                when {
                    counter[S[n]] == 3 -> answerVal--
                    counter[S[n]] == 2 -> answerVal++
                }
            }
            // b가 더 작은 범위인 경우 -> 제거
            for (n in b + 1..pb) {
                counter[S[n]]--
                when {
                    counter[S[n]] == 2 -> answerVal++
                    counter[S[n]] == 1 -> answerVal--
                }
            }
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            for (n in pa until a) {
                counter[S[n]]--
                when {
                    counter[S[n]] == 2 -> answerVal++
                    counter[S[n]] == 1 -> answerVal--
                }
            }
            answer[i] = answerVal
        }
        println(answer.joinToString("\n"))
    }
}