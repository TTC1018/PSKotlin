package baekjoon.networkflow

class `2316` {
    fun solution() {
        val (n, P) = readln().split(" ").map(String::toInt)
        val N = n shl 1
        val G = List(N) { mutableMapOf<Int, Int>() }
        repeat(N.floorDiv(2)) {
            // 각 정점을 입구/출구 정점으로 나눔
            G[it * 2][it * 2 + 1] = 1
            G[it * 2 + 1][it * 2] = 0
        }
        repeat(P) {
            val (a, b) = readln().split(" ").map { it.toInt() - 1 }
            // a 출구 -> b 입구
            G[a * 2 + 1][b * 2] = 1
            // b 입구 -> a 출구
            G[b * 2][a * 2 + 1] = 0
            // b 출구 -> a 입구
            G[b * 2 + 1][a * 2] = 1
            // a 입구 -> b 출구
            G[a * 2][b * 2 + 1] = 0
        }
        val F = List(N) { IntArray(N) }
        loop@ while (true) {
            val V = IntArray(N) { -1 }
            val q = ArrayDeque<Pair<Int, Int>>().apply {
                // 1번이 출발점
                this += 1 to 0
            }
            while (q.isNotEmpty()) {
                val (now, prev) = q.removeFirst()
                if (V[now] < 0) {
                    V[now] = prev
                    // 2번이 도착지점
                    if (now == 2) {
                        var x = 2
                        while (x > 0) {
                            val p = V[x]
                            F[p][x]++
                            F[x][p]--
                            x = p
                        }
                        continue@loop
                    }

                    G[now].keys.forEach { next ->
                        if (G[now][next]!! > F[now][next]) {
                            q += next to now
                        }
                    }
                }
            }
            break
        }
        print(F.sumOf { it[2] })
    }
}