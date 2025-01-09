package baekjoon.dp

class `9658` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val dp = BooleanArray(1000 + 1) { false }.apply {
            this[2] = true
            this[4] = true
            val target = listOf(1, 3, 4)
            // 내가 돌을 1,3,4개 가져갔을 때
            // 상대가 이길 수 없는 경우에 처하면 승리
            (5..1000).forEach { target.forEach { m -> if (this[it - m].not()) this[it] = true } }
        }
        print(if (dp[N]) "SK" else "CY")
    }
}