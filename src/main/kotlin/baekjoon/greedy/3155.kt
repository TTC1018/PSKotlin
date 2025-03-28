package baekjoon.greedy

import kotlin.math.absoluteValue

class `3155` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val T = readLine().split(" ").map(String::toInt)
        val B = readLine().split(" ").map(String::toInt)
        var now = 0
        val sb = StringBuilder()
        for (i in 0 until N - 1) {
            if (now !in B[i + 1] + 1..T[i + 1] - 1) {
                now = if ((now - (B[i + 1] + 1)).absoluteValue <= (now - (T[i + 1] - 1)).absoluteValue) {
                    B[i + 1] + 1
                } else {
                    T[i + 1] - 1
                }
            }
            sb.append(now).append(' ')
        }
        sb.append(now).append(' ')
        print(sb.dropLast(1))
    }
}