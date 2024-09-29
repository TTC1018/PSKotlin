package baekjoon.math

import kotlin.math.pow

class `27965` {
    fun solution() = with(System.`in`.bufferedReader()) {
        // 실제로 나머지 계산을 할 때를 떠올려보기
        val (N, K) = readLine().split(" ").map(String::toLong)
        var answer = 0L
        for (n in 1..N) {
            var l = "$n".length
            answer *= (10.0).pow(l).toLong()
            answer += n
            answer %= K
        }
        print(answer)
    }
}