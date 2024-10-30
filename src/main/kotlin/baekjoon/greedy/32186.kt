package baekjoon.greedy

import kotlin.math.absoluteValue

class `32186` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toLong)
        val A = readLine().split(" ").map(String::toLong)
        var (s, e) = 0 to A.lastIndex
        var answer = 0L
        while (s < e) {
            val diff = (A[s++] - A[e--]).absoluteValue
            if (diff % K <= K / 2) {
                val d = diff.floorDiv(K)
                val m = diff.mod(K)
                answer += d + m
            } else { // 작은 값을 더 큰 값으로 만들고 상대 값을 증가시키기
                val d = diff.floorDiv(K) + 1
                val m = (K - diff.mod(K))
                answer += d + m
            }
        }
        print(answer)
    }
}