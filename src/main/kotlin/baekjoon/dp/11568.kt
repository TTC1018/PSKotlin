package baekjoon.dp

class `11568` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val C = readLine().split(" ").map(String::toInt)
        val dp = IntArray(N) { 1 }
        for (i in 1 until N) {
            for (j in 0 until i) {
                if (C[j] < C[i]) {
                    dp[i] = dp[i].coerceAtLeast(dp[j] + 1)
                }
            }
        }
        print(dp.maxOf { it })
    }
}