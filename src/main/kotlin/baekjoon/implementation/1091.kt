package baekjoon.implementation

class `1091` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val P = readLine().split(" ").map(String::toInt)
        val S = readLine().split(" ").map(String::toInt)
        var C = IntArray(N) { it }
        val firstC = C.clone()
        var tried = 0
        var idx = 0
        if (C.all { P[it] == idx++ % 3 }) {
            print(0)
            return
        }
        var nextC = IntArray(N).apply {
            for (i in S.indices) {
                this[S[i]] = C[i]
            }
        }
        C = nextC
        tried++

        while (true) {
            idx = 0
            if (C.all { P[it] == idx++ % 3 }) {
                print(tried)
                return
            }

            if (C.contentEquals(firstC)) {
                print(-1)
                return
            }

            nextC = IntArray(N).apply {
                for (i in S.indices) {
                    this[S[i]] = C[i]
                }
            }
            C = nextC
            tried++
        }
    }
}