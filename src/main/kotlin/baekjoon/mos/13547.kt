package baekjoon.mos

import kotlin.math.sqrt

class `13547` {
    private data class Query(
        val a: Int,
        val b: Int,
        val i: Int,
    ) : Comparable<Query> {
        /**
         * - 쿼리를 효율적으로 처리하기 위해 정렬 기준이 필요
         * - 그 정렬 기준은 제곱근 분할법을 따름
         * - 각 쿼리의 시작지점과 끝지점이 어떠한 블록에 속한다고 가정하고,
         * 블록을 기준으로 차이가 적은 쿼리를 순차적으로 처리하도록 정렬하는 것임
         */
        override fun compareTo(other: Query): Int {
            val myC = (a / nSqrt).toInt()
            val otherC = (other.a / nSqrt).toInt()
            if (myC == otherC) return b.compareTo(other.b)
            return myC.compareTo(otherC)
        }

        companion object {
            var nSqrt = 0.0
        }
    }

    fun solution() {
        val N = readln().toInt().also { Query.nSqrt = sqrt(it.toDouble()) }
        val A = readln().split(" ").map(String::toInt)
        val M = readln().toInt()
        val Q = List(M) { i ->
            readln().split(" ").map { it.toInt() - 1 }.let { Query(it[0], it[1], i) }
        }.sorted()
        // 쿼리마다 센 숫자 개수를 기록
        val counter = IntArray(1e6.toInt() + 1)
        val answer = IntArray(M)
        // 이전 쿼리를 저장할 변수
        var (pS, pE) = Q[0].a to Q[0].a - 1
        var cnt = 0
        // 이전 쿼리와 비교하면서 중복 부분 살리기
        Q.forEach { (a, b, i) ->
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            (pS until a).forEach {
                if (--counter[A[it]] == 0) cnt--
            }
            // a가 더 작은 범위인 경우 -> 추가
            (a until pS).forEach {
                if (++counter[A[it]] == 1) cnt++
            }
            // b가 더 큰 범위인 경우 -> 추가
            (pE + 1..b).forEach {
                if (++counter[A[it]] == 1) cnt++
            }
            // b가 더 작은 범위인 경우 -> 제거
            (b + 1..pE).forEach {
                if (--counter[A[it]] == 0) cnt--
            }
            answer[i] = cnt
            pS = a; pE = b
        }
        print(answer.joinToString("\n"))
    }
}