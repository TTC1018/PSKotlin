package baekjoon.mos

import java.util.StringTokenizer
import kotlin.math.sqrt

class `6515` {
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

    private val BASE = 100000

    fun main() = with(System.`in`.bufferedReader()) {
        print(
            StringBuilder().apply {
                while (true) {
                    val st = StringTokenizer(readLine())
                    val n = st.nextToken().toInt()
                    if (n == 0) break
                    Query.nSqrt = sqrt(n.toDouble()).toInt()
                    val q = st.nextToken().toInt()
                    val A = IntArray(n).apply {
                        StringTokenizer(readLine()).let {
                            var i = 0
                            while (it.hasMoreTokens()) {
                                this[i++] = BASE + it.nextToken().toInt()
                            }
                        }
                    }
                    val Q = List(q) { i ->
                        StringTokenizer(readLine()).let {
                            Query(it.nextToken().toInt() - 1, it.nextToken().toInt() - 1, i)
                        }
                    }.sorted()
                    val answer = IntArray(q)
                    var maxVal = 0
                    val countCounter = IntArray(n + 1)
                    val counter = IntArray(BASE * 2 + 1).apply {
                        val (s, e, qi) = Q.first()
                        for (i in s..e) {
                            countCounter[this[A[i]]]--
                            ++countCounter[++this[A[i]]]
                            maxVal = maxVal.coerceAtLeast(this[A[i]])
                        }
                        answer[qi] = maxVal
                    }
                    for (j in 1 until q) {
                        val (pa, pb, _) = Q[j - 1]
                        val (a, b, qi) = Q[j]
                        // a가 더 작은 범위인 경우 -> 추가
                        for (i in a until pa) {
                            countCounter[counter[A[i]]]--
                            ++countCounter[++counter[A[i]]]
                            maxVal = maxOf(maxVal, counter[A[i]])
                        }
                        // b가 더 큰 범위인 경우 -> 추가
                        for (i in pb + 1..b) {
                            countCounter[counter[A[i]]]--
                            ++countCounter[++counter[A[i]]]
                            maxVal = maxOf(maxVal, counter[A[i]])
                        }
                        // b가 더 작은 범위인 경우 -> 제거
                        for (i in b + 1..pb) {
                            if (--countCounter[counter[A[i]]] == 0 && maxVal == counter[A[i]]) maxVal--
                            countCounter[--counter[A[i]]]++
                        }
                        // a보다 작은 범위를 쿼리한 경우 -> 제거
                        for (i in pa until a) {
                            if (--countCounter[counter[A[i]]] == 0 && maxVal == counter[A[i]]) maxVal--
                            countCounter[--counter[A[i]]]++
                        }
                        answer[qi] = maxVal
                    }
                    for (ans in answer) {
                        appendLine(ans)
                    }
                }
            }.dropLast(1)
        )
    }

}