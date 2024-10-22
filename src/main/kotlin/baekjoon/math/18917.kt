package baekjoon.math

class `18917` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        var answer = 0L
        var bit = 0
        repeat(readLine().toInt()) {
            val ops = readLine().split(" ").map(String::toInt)
            when (ops.first()) {
                1 -> {
                    answer += ops.last()
                    bit = bit xor ops.last()
                }

                2 -> {
                    answer -= ops.last()
                    bit = bit xor ops.last()
                }

                3 -> sb.appendLine(answer)
                4 -> sb.appendLine(bit) // 홀수 개수 숫자의 XOR은 자기자신
            }
        }
        print(sb.dropLast(1))
    }
}