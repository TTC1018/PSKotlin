package baekjoon.networkflow

class `11408` {
    fun solution() {
        val (N, M) = readln().split(" ").map(String::toInt)
        val L = N + M + 1
        val G = List(L + 1) { mutableListOf<Int>() }
        val F = List(L + 1) { IntArray(L + 1) }
        val CP = List(L + 1) { IntArray(L + 1) }
        val C = List(L + 1) { IntArray(L + 1) }
        repeat(N) {
            // 모든 직원 정점을 source에 연결
            val i = it + 1
            G[0] += i
            G[i] += 0
            CP[0][i] = 1

            // 일 정점과 연결
            val J = readln().split(" ").drop(1).map(String::toInt)
            for (j in 0..J.lastIndex step 2) {
                val (n, p) = N + J[j] to J[j + 1]
                G[n] += i
                G[i] += n
                CP[i][n] = 1
                C[i][n] = p
                C[n][i] = -p
            }
        }

        // 모든 일 정점을 sink에 연결
        (N + 1..L).forEach {
            G[it] += L
            G[L] += it
            CP[it][L] = 1
        }

        var answerCnt = 0
        var answerSum = 0
        while (true) {
            // 각 정점의 이전 정점
            val prev = IntArray(L + 1) { -1 }
            val dist = IntArray(L + 1) { Int.MAX_VALUE }
            val V = BooleanArray(L + 1) { false }

            val q = ArrayDeque<Int>().apply {
                V[0] = true
                dist[0] = 0
                this += 0
            }

            while (q.isNotEmpty()) {
                val now = q.removeFirst()
                V[now] = false

                for (next in G[now]) {
                    val nextDist = dist[now] + C[now][next]
                    if (CP[now][next] > F[now][next] && dist[next] > nextDist) {
                        dist[next] = nextDist
                        prev[next] = now
                        if (V[next].not()) {
                            V[next] = true
                            q += next
                        }
                    }
                }
            }

            // sink에 도달 못 했을 때
            if (prev.last() == -1) break
            var now = L
            while (now > 0) {
                F[prev[now]][now]++
                F[now][prev[now]]--
                answerSum += C[prev[now]][now]
                now = prev[now]
            }
            answerCnt++
        }

        print("$answerCnt\n$answerSum")
    }
}