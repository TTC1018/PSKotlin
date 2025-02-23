package baekjoon.bipartitematching

import kotlin.math.roundToInt
import kotlin.math.sqrt

class `1017` {
    companion object {
        private const val MAX = 2000
    }

    private val P = buildSet {
        addAll(2..MAX)
        val end = sqrt(2000.0).roundToInt()
        for (n in 2..end) {
            for (i in 2..MAX.floorDiv(n)) {
                remove(n * i)
            }
        }
    }

    private lateinit var nums: MutableList<Int>
    private val V = mutableSetOf<Int>()
    private val paired = mutableMapOf<Int, Int>()

    private fun search(x: Int): Boolean {
        if (x in V) return false
        V += x

        for (other in nums) {
            // 페어 안 됐거나, 이미 페어 됐으면 x와 페어되도록 교체
            if (x + other in P && (other !in paired || search(paired[other]!!))) {
                paired[other] = x
                return true
            }
        }
        return false
    }

    fun solution() {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
        if (N == 2) {
            print(A.last().takeIf { A[0] + it in P } ?: -1)
            return
        }

        val answer = mutableListOf<Int>()
        for (i in 1 until N) {
            paired.clear()
            if (A[0] + A[i] in P) {
                nums = A.toMutableList().apply {
                    remove(A[0])
                    remove(A[i])
                }
                nums.forEach { num ->
                    V.clear()
                    search(num)
                }
            }
            if (paired.size == N - 2) answer += A[i]
        }
        print(answer.takeIf { it.isNotEmpty() }?.also { it.sort() }?.joinToString(" ") ?: -1)
    }
}