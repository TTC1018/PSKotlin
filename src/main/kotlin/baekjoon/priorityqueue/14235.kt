package baekjoon.priorityqueue

import java.util.PriorityQueue

class `14235` {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().trim().toInt()
        val pq = PriorityQueue<Int>(reverseOrder())
        val sb = StringBuilder()
        repeat(n) {
            val a = readLine().trim().split(" ").map(String::toInt)
            when (a.first()) {
                0 -> sb.appendLine(pq.takeIf { it.isNotEmpty() }?.remove() ?: -1)
                else -> pq.addAll(a.drop(1))
            }
        }
        print(sb.dropLast(1))
    }
}