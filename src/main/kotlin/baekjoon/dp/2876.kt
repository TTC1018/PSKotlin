package baekjoon.dp

import kotlin.math.absoluteValue

class `2876` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val B = List(N) { readLine().split(" ").map(String::toInt) }
        var answerNum = B.first().minOf { it }
        var answer = 1
        val dp = List(N) { IntArray(2) { 1 } }
        for (i in 1 until N) {
            for (j in 0..1) {
                val crossed = (j - 1).absoluteValue
                if (B[i - 1][crossed] == B[i][j]) {
                    dp[i][j] = dp[i][j].coerceAtLeast(dp[i - 1][crossed] + 1)
                }
                if (B[i - 1][j] == B[i][j]) {
                    dp[i][j] = dp[i][j].coerceAtLeast(dp[i - 1][j] + 1)
                }

                if (dp[i][j] >= answer) {
                    if (dp[i][j] == answer && B[i][j] < answerNum) {
                        answerNum = B[i][j]
                    } else if (dp[i][j] > answer) {
                        answerNum = B[i][j]
                        answer = dp[i][j]
                    }
                }
            }
        }
        print("$answer $answerNum")
    }
}