package baekjoon.greedy

class `31589` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toLong)
        val T = readLine().split(" ").map(String::toLong).sorted()
        // 제일 큰 값으로 시작
        // 점진적으로 먹어도 양 극단 선택하는 것보다 미비함
        var answer = T.last()
        var left = K - 1
        var i = 0
        while (i < N.div(2) && left >= 2) {
            left -= 2
            answer += (T[T.lastIndex - (i + 1)] - T[i++])
        }
        print(answer)
    }
}