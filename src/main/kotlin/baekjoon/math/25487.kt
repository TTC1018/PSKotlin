package baekjoon.math

class `25487` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            sb.appendLine(readLine().split(" ").map(String::toInt).minOf { it })
        }
        print(sb.dropLast(1))
    }
}