package baekjoon.hashmap

class `3077` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val A = readLine().split(" ")
        val H = readLine().split(" ")
        val I = A.withIndex().associate { it.value to it.index }
        val total = N * (N - 1) / 2
        var score = 0
        for (i in H.indices) {
            for (j in i + 1 until N) {
                score += if (I[H[i]]!! < I[H[j]]!!) 1 else 0
            }
        }
        print("$score/$total")
    }
}