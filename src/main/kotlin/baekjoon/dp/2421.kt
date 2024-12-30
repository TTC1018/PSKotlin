package baekjoon.dp

class `2421` {
    private val P = BooleanArray(1e6.toInt() + 1) { true }.apply {
        fill(false, 0, 2)
        for (n in 2..1e3.toInt()) {
            for (i in 2..1e6.toInt().floorDiv(n)) {
                this[n * i] = false
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val dp = List(N + 1) { IntArray(N + 1) }
        var answer = 1
        for (i in 1..N) {
            for (j in 1..N) {
                if (i == 1 && j == 1) continue

                val now = "$i$j".toInt()
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                if (P[now]) {
                    dp[i][j]++
                }
                answer = maxOf(dp[i][j], answer)
            }
        }
        print(answer)
    }
}