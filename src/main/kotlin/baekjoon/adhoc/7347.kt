package baekjoon.adhoc

import kotlin.math.absoluteValue

class `7347` {
    fun solution() = with(System.`in`.bufferedReader()) {
        print(
            StringBuilder().apply {
                repeat(readLine().trim().toInt()) {
                    val s = readLine().trim().split(" ").drop(1).map(String::toInt)
                    // 홀수 개면 홀짝 인덱스 스위칭 가능
                    // 짝수 개면 홀수/짝수 인덱스에만 존재할 수 있음
                    // 즉, 홀짝 인덱스가 하나의 클러스터를 이루려면 1개 이하로 차이가 나야됨
                    appendLine(
                        "YES".takeIf {
                            s.size and 1 == 1 || s.withIndex().run {
                                (count { (i, v) -> i and 1 == 0 && v == 1 } -
                                        count { (i, v) -> i and 1 == 1 && v == 1 }).absoluteValue <= 1
                            }
                        } ?: "NO"
                    )
                }
            }.dropLast(1)
        )
    }
}