package baekjoon.binarysearch

class `28357` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toLong)
        val A = readLine().split(" ").map(String::toLong)
        var (l, r) = 0L to A.maxOf { it }
        var answer = 0L
        while (l <= r) {
            val m = (l + r).div(2)
            val sumVal = A.sumOf { (it - m).coerceAtLeast(0L) }
            if (sumVal <= K) {
                r = m - 1
                answer = m
            } else {
                l = m + 1
            }
        }
        print(answer)
    }
}