package baekjoon.graph

class `24447` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, R) = readLine().split(" ").map(String::toInt)
        val B = buildMap<Int, MutableList<Int>> {
            repeat(M) {
                val (u, v) = readLine().split(" ").map(String::toInt)
                getOrPut(u) { mutableListOf() }.add(v)
                getOrPut(v) { mutableListOf() }.add(u)
            }
            for ((_, v) in this) {
                v.sort()
            }
        }

        val D = LongArray(N + 1) { -1L }.apply { this[R] = 0L }
        val C = LongArray(N + 1) { 0 }.apply { this[R] = 1L }
        val q = ArrayDeque<Int>().apply { add(R) }
        var t = 1L
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            B[now]?.forEach { next ->
                if (D[next] == -1L) {
                    D[next] = D[now] + 1
                    C[next] = ++t
                    q.add(next)
                }
            }

        }
        print(D.zip(C).sumOf { (d, c) -> d * c })
    }
}