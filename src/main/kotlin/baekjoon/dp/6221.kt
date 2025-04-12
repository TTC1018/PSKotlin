package baekjoon.dp

class `6221` {
    fun solution() {
        val N = readln().toInt()
        val H = List(N) { readln().split(" ").map(String::toInt) }
            .sortedWith(compareBy({ it[0] }, { it[1] }))
        val dp = List(N + 1) { BooleanArray(N) { false } }.apply {
            repeat(N) { this[1][it] = true }
        }
        var answer = 1
        for (n in 2..N) {
            loop@ for (i in 0 until N) {
                for (j in i + 1 until N) {
                    if (H[i][0] < H[j][0] && H[i][1] < H[j][1] && dp[n - 1][j]) {
                        dp[n][i] = true
                        answer = n
                        continue@loop
                    }
                }
            }
        }
        print(answer)
    }
}