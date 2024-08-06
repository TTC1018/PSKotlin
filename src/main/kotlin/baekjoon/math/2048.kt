package baekjoon.math

import kotlin.math.pow

class `2048` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val (l, r) = readLine().split(" ").map(String::toInt)
            if (r >= 4) {
                sb.appendLine(r)
            } else {
                var num = (l..r).joinToString("") { "${(2.0).pow(it).toInt()}" }.toInt()
                var answer = 0
                while (num % 2 == 0) {
                    answer++
                    num /= 2
                }
                sb.appendLine(answer)
            }
        }
        print(sb.dropLast(1))
    }
}