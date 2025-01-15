package baekjoon.dp

class `24392` {
    private val MOD = 1e9.toInt() + 7

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        val B = List(N) { readLine().split(" ").map(String::toInt) }
        val dp = List(N) { IntArray(M) }.apply {
            for (j in 0 until M) {
                if (B.last()[j] == 1) this[lastIndex][j]++
            }
        }
        for (i in N - 2 downTo 0) {
            for (j in 0 until M) {
                if (B[i][j] == 1) {
                    if (B[i + 1].getOrNull(j - 1) == 1) {
                        dp[i][j] += dp[i + 1][j - 1]
                        dp[i][j] %= MOD
                    }
                    if (B[i + 1][j] == 1) {
                        dp[i][j] += dp[i + 1][j]
                        dp[i][j] %= MOD
                    }
                    if (B[i + 1].getOrNull(j + 1) == 1) {
                        dp[i][j] += dp[i + 1][j + 1]
                        dp[i][j] %= MOD
                    }
                }
            }
        }
        print(dp.first().fold(0) { acc, v -> (acc + v) % MOD })
    }

}