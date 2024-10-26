package baekjoon.priorityqueue

import java.util.*

class `31860` {
    fun main() = with(System.`in`.bufferedReader()) {
        // 값이 커지려면 무조건 큰 중요도부터 처리
        val (N, M, K) = readLine().split(" ").map(String::toInt)
        var answer = 0
        val q = PriorityQueue<Int>(reverseOrder()).apply {
            repeat(N) { add(readLine().toInt()) }
        }
        var cnt = 0
        val sb = StringBuilder()
        while (q.isNotEmpty()) {
            val big = q.remove()
            answer = answer.floorDiv(2) + big
            sb.appendLine(answer)
            cnt++
            val next = big - M
            if (next > K) {
                q.add(next)
            }
        }
        print("$cnt\n${sb.dropLast(1)}")
    }
}