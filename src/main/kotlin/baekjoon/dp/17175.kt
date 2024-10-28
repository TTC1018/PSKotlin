package baekjoon.dp

class `17175` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val MOD = 1e9.toLong() + 7
        val n = readLine().toInt()
        val f = LongArray(n + 1).apply {
            this[0] = 1
            if (n >= 1) this[1] = 1
            if (n >= 2) this[2] = 3
            for (i in 3..n) {
                this[i] += this[i - 1]
                this[i] %= MOD
                this[i] += this[i - 2]
                this[i] %= MOD
                this[i]++
                this[i] %= MOD
            }
        }
        print(f.last())
    }
}