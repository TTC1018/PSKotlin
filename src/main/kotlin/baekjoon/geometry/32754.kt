package baekjoon.geometry

import kotlin.math.absoluteValue
import kotlin.math.sqrt

class `32754` {
    fun solution() {
        val (N, R) = readln().split(' ').map(String::toInt)
        val S = List(N) {
            readln().split(" ").map(String::toDouble)
                .let {
                    // 중심 + 원점까지의 최대 거리
                    val (bx, sx) = maxOf(it[0], it[4]) to minOf(it[0], it[4])
                    val (by, sy) = maxOf(it[1], it[5]) to minOf(it[1], it[5])
                    val (x, y) = sx + (bx - sx).div(2) to
                            sy + (by - sy).div(2)
                    val d =
                        (x - it[0]).absoluteValue.run { this * this } + (y - it[1]).absoluteValue.run { this * this }
                    Triple(x, y, d)
                }
        }
        print(S.count { (x, y, d) -> sqrt(x * x + y * y) - sqrt(d) <= R })
    }
}