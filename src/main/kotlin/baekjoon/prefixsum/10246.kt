package baekjoon.prefixsum

class `10246` {
    private val MAX = 1e6.toInt()

    fun solution() {
        val P = IntArray(MAX + 1).apply {
            this[1] = 2
            for (n in 2..lastIndex) {
                this[n] += this[n - 1] + (n + 1)
            }
        }
        val answer = IntArray(MAX + 1).apply {
            for (i in 0..MAX) {
                for (j in i..MAX) {
                    (P[j] - P[i])
                        .takeIf { it <= MAX }
                        ?.let { this[it]++ } ?: break
                }
            }
        }
        val sb = StringBuilder()
        while (true) {
            val N = readln().toInt()
            if (N == 0) break
            sb.appendLine(answer[N])
        }
        print(sb.dropLast(1))
    }
}