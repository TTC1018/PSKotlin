package baekjoon.math

class `17827` {
    fun solution() {
        val (N, M, V) = readln().split(" ").map(String::toInt)
        val C = readln().split(" ").map(String::toInt)
        print(
            StringBuilder().apply {
                repeat(M) {
                    var K = readln().toInt()
                    if (K < N) {
                        appendLine(C[K])
                    } else {
                        K -= N
                        appendLine(C[V - 1 + K % (N - (V - 1))])
                    }
                }
            }.dropLast(1)
        )
    }
}