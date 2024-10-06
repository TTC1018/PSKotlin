package baekjoon.math

import kotlin.math.absoluteValue

class `26646` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val H = readLine().split(" ").map(String::toInt)
        print(H.zipWithNext { p, n -> (p + n) * (p + n) + (p - n).absoluteValue.run { this * this } }.sumOf { it })
    }
}