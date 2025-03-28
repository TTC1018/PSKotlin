package baekjoon.dp

class `31861` {
    private val MOD = 1e9.toInt() + 7
    fun solution() {
        val (N, M) = readln().split(" ").map(String::toInt)
        val dp = List(M) { IntArray(26) }.apply {
            this[0].fill(1)
        }

        for (n in 1 until M) {
            for (i in 0 until 26) {
                val under = (i - N).coerceAtLeast(-1)
                val over = (i + N).coerceAtMost(26)
                for (u in 0..under) {
                    dp[n][i] += dp[n - 1][u]
                    dp[n][i] %= MOD
                }
                for (o in over until 26) {
                    dp[n][i] += dp[n - 1][o]
                    dp[n][i] %= MOD
                }
                if (N == 0) dp[n][i] -= dp[n - 1][i]
            }
        }
        print(dp.last().fold(0) { acc, n -> (acc + n).mod(MOD) })
    }
}