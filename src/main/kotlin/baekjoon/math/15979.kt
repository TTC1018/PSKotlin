package baekjoon.math

import kotlin.math.absoluteValue

class `15979` {
    private fun gcd(x: Long, y: Long): Long {
        var (a, b) = listOf(x, y).sortedDescending()
        while (b > 0) {
            val mod = a % b
            a = b
            b = mod
        }
        return a
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (M, N) = readLine().split(" ").map { it.toLong().absoluteValue }
        // (M-1, 1) 혹은 (1, N-1)로 이동 후, (M, N) 도착
        // 최대공약수가 1이면 한번에 갈 수 있음
        print(gcd(M, N).coerceAtMost(2))
    }
}