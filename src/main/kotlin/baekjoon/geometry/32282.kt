package baekjoon.geometry

import kotlin.math.ceil
import kotlin.math.sqrt

class `32282` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (X, Y, c) = readLine().split(" ").map(String::toInt)
        when {
            X == 0 && Y == 0 -> print(0)
            X * X + Y * Y == c * c -> print(1)
            X * X + Y * Y < c * c -> print(2) // 교차 지점으로 간 다음, 목표 위치로 이동
            else -> print(ceil(sqrt(X.toDouble() * X + Y.toDouble() * Y).div(c)).toInt()) // 근처로 이동, 나누어 떨어지지 않으면 교차 지점으로 가고 이동
        }
    }
}