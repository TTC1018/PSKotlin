package baekjoon.math

class `13251` {
    fun solution() {
        val M = readln().toInt()
        val C = readln().split(" ").map(String::toInt)
        val K = readln().toInt()
        // N개에서 K개 뽑기
        // 색상은 1~M
        // 모두 같을 확률은?
        // 색상 뽑기 c/N
        val N = C.sumOf { it }
        var answer = 0.0
        for (c in C) {
            if (c >= K) {
                var temp = 1.0
                var cnt = 0.0
                while (cnt < K) {
                    temp *= (c - cnt).div(N - cnt)
                    cnt++
                }
                answer += temp
            }
        }
        print(answer)
    }
}