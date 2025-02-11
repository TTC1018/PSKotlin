package baekjoon.greedy

import java.util.StringTokenizer

class `9048` {
    fun main() {
        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    val (F, R, N) = readln().split(" ").map(String::toInt)
                    val B = List(F) { mutableListOf<Int>(0) }.apply {
                        repeat(N) {
                            val (a, b) = StringTokenizer(readln()).run { nextToken().toInt() to nextToken().toInt() }
                            this[a - 1] += b
                        }
                        forEach { it += (R + 1); it.sort() }
                    }
                    var answer = F + F + 1 + R
                    B.forEach { floor ->
                        answer += floor
                            .takeIf { it.size > 1 }
                            ?.zipWithNext { a, b -> a + (R + 1 - b) }
                            ?.minOf { it }
                            ?.times(2) ?: 0
                    }
                    appendLine(answer)
                }
            }.dropLast(1)
        )

    }
}