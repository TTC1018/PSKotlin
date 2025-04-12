package baekjoon.dp

class `15131` {
    fun solution() {
        val n = readln().toInt()
        val nums = listOf(6, 2, 5, 5, 4, 5, 6, 3, 7, 6)
        val dp = IntArray(n + 1).apply {
            this[1] = Int.MIN_VALUE
            this[2] = 1
        }
        for (i in 2..n) {
            nums.forEachIndexed { num, v ->
                dp.getOrNull(i - v)?.run {
                    dp[i] = dp[i].coerceAtLeast(this + num)
                }
            }
        }
        print(dp.last())
    }
}