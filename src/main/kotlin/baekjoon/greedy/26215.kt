package baekjoon.greedy

import java.util.*

class `26215` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val a = readLine().split(" ").map(String::toInt)
        val q = PriorityQueue<Int>(reverseOrder()).apply {
            addAll(a)
        }
        var answer = 0
        while (q.isNotEmpty()) {
            val h1 = q.remove()
            val h2 = q.takeIf { it.isNotEmpty() }?.remove()

            answer++
            h1.takeIf { it > 1 }?.let { q.add(it - 1) }
            h2?.takeIf { it > 1 }?.let { q.add(it - 1) }
        }

        if (answer > 1440) {
            print(-1)
        } else {
            print(answer)
        }
    }
}