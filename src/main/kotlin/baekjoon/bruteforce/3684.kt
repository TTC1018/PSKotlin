package baekjoon.bruteforce

class `3684` {
    private val MOD = 10001

    fun solution() = with(System.`in`.bufferedReader()) {
        val T = readLine().toInt()
        val x = List(T) { readLine().toInt() }
        for (a in 0..10000) {
            loop@ for (b in 0..10000) {
                for (i in 0 until T - 1) {
                    if (x[i + 1] != (a * ((a * x[i] + b) % MOD) + b) % MOD)
                        continue@loop
                }

                print(
                    StringBuilder().apply {
                        x.forEach { xi ->
                            appendLine((a * xi + b) % MOD)
                        }
                    }.dropLast(1)
                )
                return
            }
        }
    }
}