package baekjoon.math

class `12910` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val B = readLine().split(" ").map(String::toInt).run {
            IntArray(50 + 1).apply {
                this@run.forEach { this[it]++ }
            }
        }
        var stacked = 1
        var answer = 0
        for (c in 1..50) {
            if (B[c] == 0) break

            stacked *= B[c]
            answer += stacked
        }
        print(answer)
    }
}