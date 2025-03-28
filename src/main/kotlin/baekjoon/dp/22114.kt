package baekjoon.dp

class `22114` {
    fun solution() {
        val (N, K) = readln().split(' ').map(String::toInt)
        val L = readln().split(" ").map(String::toInt)
        val dp = List(N) { IntArray(2) { 1 } }
        var answer = 1
        for (i in dp.lastIndex downTo 1) {
            if (L[i - 1] > K) {
                dp[i - 1][1] = maxOf(dp[i - 1][1], dp[i][0] + 1)
            } else {
                dp[i - 1][1] = maxOf(dp[i - 1][1], dp[i].maxOf { it } + 1)
                dp[i - 1][0] = maxOf(dp[i - 1][0], dp[i][0] + 1)
            }
            answer = maxOf(answer, dp[i - 1].maxOf { it })
        }
        print(answer)
    }
}