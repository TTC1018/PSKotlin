package baekjoon.networkflow

class `11406` {
    fun solution() {
        val (N, M) = readln().split(' ').map(String::toInt)
        val L = N + M
        val (s, e) = 0 to L + 1
        val G = List(e + 1) { mutableListOf<Int>() }
        val cost = List(e + 1) { IntArray(e + 1) }
        val capa = List(e + 1) { IntArray(e + 1) }
        val F = List(e + 1) { IntArray(e + 1) }
        val A = readln().split(" ").map(String::toInt).also {
            // 사람은 M+1부터 시작
            it.forEachIndexed { i, v ->
                G[M + (i + 1)] += e
                G[e] += M + (i + 1)
                // 사람->sink
                capa[M + (i + 1)][e] = v
            }
        }
        val B = readln().split(" ").map(String::toInt).also {
            // 서점은 1부터 시작
            it.forEachIndexed { i, v ->
                G[s] += i + 1
                G[i + 1] += s
                // source->서점
                capa[s][i + 1] = v
            }
        }
        repeat(M) { i ->
            val C = readln().split(" ").map(String::toInt)
            C.forEachIndexed { j, v ->
                // 서점 - 사람 연결
                G[i + 1] += M + j + 1
                G[M + j + 1] += i + 1
                capa[i + 1][M + j + 1] = v
            }
        }

        var answerCnt = 0
        while (true) {
            val prev = IntArray(e + 1) { -1 }
            val q = ArrayDeque<Int>().apply {
                this += s
            }
            while (q.isNotEmpty()) {
                val now = q.removeFirst()
                G[now].forEach { next ->
                    if (capa[now][next] > F[now][next] && prev[next] == -1) {
                        prev[next] = now
                        q += next
                    }
                }
            }
            if (prev.last() == -1) break
            var now = e
            var cnt = Int.MAX_VALUE // 현재 보낼 수 있는 유량
            while (now > s) {
                val next = prev[now]
                cnt = cnt.coerceAtMost(capa[next][now] - F[next][now])
                now = next
            }
            now = e
            while (now > s) {
                val next = prev[now]
                F[next][now] += cnt
                F[now][next] -= cnt
                now = next
            }
            answerCnt += cnt
        }
        print(answerCnt)
    }
}