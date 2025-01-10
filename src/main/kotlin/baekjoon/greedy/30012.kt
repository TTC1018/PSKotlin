package baekjoon.greedy

import kotlin.math.absoluteValue

class `30012` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (S, N) = readLine().split(" ").map(String::toLong)
        val E = readLine().split(" ").map(String::toLong)
        val (K, L) = readLine().split(" ").map(String::toLong)
        // 두 개구리는 같이 움직임
        // K - d 만큼 체력 소모, 걸으면 L 소모
        // 점프 이후 걸어가는 것은 누가 가도 합이 동일함
        // 점프는 무조건 해야됨. 안 하면 손해임
        print(
            E.mapIndexed { i, e ->
                val diff = (S - e).absoluteValue
                if (diff > 2 * K) {
                    (diff - 2 * K) * L to i + 1
                } else {
                    2 * K - diff to i + 1
                }
            }.minBy { it.first }
                .run { "$first $second" }
        )
    }
}