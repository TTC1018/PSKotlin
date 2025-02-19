package baekjoon.adhoc

class `5600` {
    fun solution() {
        val (a, b, c) = readln().split(" ").map(String::toInt)
        val ops = List(readln().toInt()) { readln().split(" ").map { it.toInt() - 1 } }
            .sortedByDescending { it.last() }
        val answer = IntArray(a + b + c) { 2 }
        for ((i, j, k, r) in ops) {
            when (r) {
                -1 -> {
                    when {
                        answer[i] == 1 && answer[j] == 1 -> answer[k] = 0
                        answer[i] == 1 && answer[k] == 1 -> answer[j] = 0
                        answer[j] == 1 && answer[k] == 1 -> answer[i] = 0
                    }
                }

                0 -> {
                    answer[i] = 1
                    answer[j] = 1
                    answer[k] = 1
                }
            }
        }
        print(answer.joinToString("\n"))
    }
}