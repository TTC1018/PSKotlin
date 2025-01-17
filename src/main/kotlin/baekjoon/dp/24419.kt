package baekjoon.dp

class `24419` {
    private val MOD = 1e9.toInt() + 7

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val g = List(n) { readLine().split(" ").map(String::toInt) }
        val dp = List(n) { IntArray(n) }.apply {
            for (i in indices) {
                this[lastIndex][lastIndex - i] = 1
                this[lastIndex - i][lastIndex] = 1
            }
            for (i in lastIndex - 1 downTo 0) {
                for (j in lastIndex - 1 downTo 0) {
                    this[i][j] += this[i][j + 1]
                    this[i][j] %= MOD
                    this[i][j] += this[i + 1][j]
                    this[i][j] %= MOD
                }
            }
        }
        var answer = 0
        for (i in dp.indices) {
            answer += dp[0][i]
            answer %= MOD
            answer += dp[i][0]
            answer %= MOD
        }
        print("$answer ${n * n}")
    }
}