package baekjoon.mos

import kotlin.math.sqrt

class `14897` {
    private data class Query(
        val a: Int,
        val b: Int,
        val i: Int,
    ) : Comparable<Query> {
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
        var compressed = 0
        val A = readln().split(" ").map(String::toInt).let {
            // 좌표 압축
            IntArray(N).apply {
                val nums = mutableMapOf<Int, Int>()
                // 10억 이하의 자연수지만, 결국 백만개 이하가 들어오기 때문에,
                // 서로 다른 숫자는 가능해야 백만개까지만 가능함
                // 그래서 실제 숫자를 compressed라는 값으로 바꿔서 표현
                // 같은 숫자들은 모두 같은 compressed 값으로 변함
                // compressed는 압축이 진행될 때마다(새로운 숫자를 만날 때마다) 증가하므로,
                // 서로 다른 숫자는 다른 compressed 값을 가짐
                it.forEachIndexed { i, num ->
                    if (num !in nums) nums[num] = compressed++
                    this[i] = nums[num]!!
                }
            }
        }
        val qLength = readln().toInt()
        val Q = List(qLength) { i ->
            readln().split(" ").map { it.toInt() - 1 }.let { Query(it[0], it[1], i) }
        }.sorted()
        var answerVal = 0
        val answer = IntArray(qLength)
        // 쿼리마다 센 숫자 개수를 기록
        val counter = IntArray(compressed).apply {
            Q.first().let { (s, e, i) ->
                (s..e).forEach {
                    if (++this[A[it]] == 1) answerVal++
                }
                answer[i] = answerVal
            }
        }
        for (j in 1 until qLength) {
            val (pa, pb, _) = Q[j - 1]
            val (a, b, i) = Q[j]
            // a보다 작은 범위를 쿼리한 경우 -> 제거
            (pa until a).forEach {
                if (--counter[A[it]] == 0) answerVal--
            }
            // a가 더 작은 범위인 경우 -> 추가
            (a until pa).forEach {
                if (++counter[A[it]] == 1) answerVal++
            }
            // b가 더 큰 범위인 경우 -> 추가
            (pb + 1..b).forEach {
                if (++counter[A[it]] == 1) answerVal++
            }
            // b가 더 작은 범위인 경우 -> 제거
            (b + 1..pb).forEach {
                if (--counter[A[it]] == 0) answerVal--
            }
            answer[i] = answerVal
        }
        print(answer.joinToString("\n"))
    }
}