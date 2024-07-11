package baekjoon.greedy

class `17451` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val v = readLine().split(" ").map(String::toLong).reversed()
        var answer = 0L
        for (vi in v) {
            if (answer <= vi) {
                answer = vi
            } else {
                val div = answer.div(vi)
                if (answer % vi != 0L) {
                    answer = vi * (div + 1)
                }
            }
        }
        print(answer)
    }
}