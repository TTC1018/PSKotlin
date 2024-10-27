package baekjoon.dp

class `7570` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val C = readLine().split(" ").map(String::toInt)
        val I = IntArray(N + 1).apply {
            for (i in C.indices) {
                this[C[i]] = i
            }
        }

        var temp = 1
        var answer = 0
        for (n in 1 until N) {
            if (I[n] < I[n + 1]) {
                temp++
            } else {
                answer = answer.coerceAtLeast(temp)
                temp = 1
            }
        }
        answer = answer.coerceAtLeast(temp)
        print(N - answer)
    }
}