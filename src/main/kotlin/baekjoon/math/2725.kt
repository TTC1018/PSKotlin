package baekjoon.math

class `2725` {
    private fun gcd(a: Int, b: Int): Int {
        var (x, y) = a to b
        while (y > 0) {
            x = y.run {
                y = x % this
                this
            }
        }
        return x
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val memo = IntArray(1000 + 1).apply {
            this[1] = 3
            for (i in 2..1000) {
                var cnt = 0
                for (j in 1..i) {
                    // 분모 분자가 서로소라면 직선 최초 통과 좌표
                    if (gcd(i, j) == 1) {
                        cnt += 2
                    }
                }
                this[i] += this[i - 1] + cnt
            }
        }

        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val N = readLine().toInt()
            sb.appendLine(memo[N])
        }
        print(sb.dropLast(1))
    }
}