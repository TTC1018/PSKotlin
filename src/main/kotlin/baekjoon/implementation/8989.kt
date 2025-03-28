package baekjoon.implementation

import kotlin.math.absoluteValue

class `8989` {
    private val String.degree
        get() = split(":").map(String::toInt).run {
            val (hh, mm) = first() % 12 to last()
            // 1시간 당 360/12 = 30도
            // 60분 -> 360/60 -> 1분당 6도
            // 1분당 시침 0.5도 이동
            val hDegree = hh * 30 + mm.toFloat().div(2)
            val mDegree = mm * 6
            (hDegree - mDegree).absoluteValue.run { minOf(360 - this, this) }
        }

    fun solution() = with(System.`in`.bufferedReader()) {
        print(
            StringBuilder().apply {
                repeat(readLine().toInt()) {
                    appendLine(readLine().split(" ").sortedWith(compareBy({ it.degree }, { it }))[2])
                }
            }.dropLast(1)
        )
    }
}