package baekjoon.bruteforce

import kotlin.system.exitProcess

class `18233` {
    private data class Member(
        val x: Int,
        val y: Int,
    )

    private val combs = mutableListOf<Int>()
    private fun combinations(now: Int, cnt: Int) {
        if (cnt == P) {
            bruteforce()
            return
        }
        if (now == N) return

        // 고르기
        combs.add(now)
        combinations(now + 1, cnt + 1)
        combs.removeLast()
        // 안 고르기
        combinations(now + 1, cnt)
    }

    private fun bruteforce() {
        val x = combs.map { M[it].x }.sumOf { it }
        val y = combs.map { M[it].y }.sumOf { it }
        if (E in x..y) {
            // 각자 가질 수 있는 최소값 배정
            combs.forEach { answer[it] = M[it].x }
            // 앞 순서부터 부족한 값 더해주기
            var left = E - x
            var ptr = 0
            while (left > 0) {
                val idx = combs[ptr]
                val need = M[idx].y - M[idx].x
                val actual = minOf(left, need)
                left -= actual
                answer[idx] += actual
                ptr++
            }
            print(answer.joinToString(" "))
            exitProcess(0)
        }
    }

    private var N = 0
    private var P = 0
    private var E = 0
    private lateinit var M: List<Member>
    private lateinit var answer: IntArray

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            N = first(); P = this[1]; E = last()
        }
        M = List(N) { readLine().split(" ").map(String::toInt).run { Member(first(), last()) } }
        answer = IntArray(N)
        combinations(0, 0)
        print(-1)
    }
}