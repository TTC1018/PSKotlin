package baekjoon.bruteforce

class `31937` {
    private data class Log(
        val t: Int,
        val a: Int,
        val b: Int,
    )

    fun main() = with(System.`in`.bufferedReader()) {
        val (N, M, K) = readLine().split(" ").map(String::toInt)
        val C = readLine().split(" ").map(String::toInt).toSet()
        val L = List(M) {
            readLine().split(" ").map(String::toInt).run {
                Log(this[0], this[1], this[2])
            }
        }.sortedBy { it.t }
        val V = mutableSetOf<Int>()
        for (n in 1..N) {
            V.clear()
            V.add(n)

            for ((_, a, b) in L) {
                if (a in V) {
                    V.add(b)
                }
            }

            if ((1..N).all { it in V == it in C }) {
                print(n)
                return
            }
        }
    }
}