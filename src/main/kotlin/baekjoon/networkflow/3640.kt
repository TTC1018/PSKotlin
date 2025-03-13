package baekjoon.networkflow

class `3640` {
    fun solution() {
        print(
            StringBuilder().apply {
                while (true) {
                    try {
                        val (v, e) = readln().split(" ").map(String::toInt)
                        val n = v shl 1
                        val (source, sink) = n to n + 1
                        val CP = List(sink + 1) { IntArray(sink + 1) }
                        val F = List(sink + 1) { IntArray(sink + 1) }
                        val C = List(sink + 1) { IntArray(sink + 1) }
                        val G = List(sink + 1) { mutableListOf<Int>() }.apply {
                            // source - 1번, 마지막 - sink 연결
                            this[source] += 0
                            this[0] += source
                            CP[source][0] = 2
                            this[n - 1] += sink
                            this[sink] += n - 1
                            CP[n - 1][sink] = 2

                            repeat(v) {
                                // 각 정점을 입구/출구 정점으로 나눔
                                val (a, b) = it * 2 to it * 2 + 1
                                this[a] += b
                                this[b] += a
                                CP[a][b] = 1
                            }
                            // 1번, 마지막 내부 경로는 2개 지나갈 수 있게 설정
                            CP[0][1] = 2
                            CP[n - 2][n - 1] = 2
                        }
                        repeat(e) {
                            val (a, b, _c) = readln().split(" ").map { it.toInt() - 1 }
                            val c = _c + 1
                            val ao = a * 2 + 1
                            val bi = b * 2
                            CP[ao][bi] = 1
                            // a 출구 -> b 입구
                            G[ao] += bi
                            C[ao][bi] = c
                            // b 입구 -> a 출구
                            G[bi] += ao
                            C[bi][ao] = -c
                        }

                        var answer = 0
                        while (true) {
                            val prev = IntArray(sink + 1) { -1 }
                            val V = BooleanArray(sink + 1) { false }
                            val D = IntArray(sink + 1) { 1e9.toInt() }
                            val q = ArrayDeque<Int>().apply {
                                this += source
                                V[source] = true
                                D[source] = 0
                            }
                            while (q.isNotEmpty()) {
                                val now = q.removeFirst()
                                V[now] = false

                                G[now].forEach { next ->
                                    val newDist = D[now] + C[now][next]
                                    if (CP[now][next] > F[now][next] && newDist < D[next]) {
                                        D[next] = newDist
                                        prev[next] = now
                                        if (V[next].not()) {
                                            V[next] = true
                                            q += next
                                        }
                                    }
                                }
                            }

                            if (prev.last() == -1) break
                            var now = sink
                            while (now > 0) {
                                val next = prev[now]
                                answer += C[next][now]
                                F[next][now]++
                                F[now][next]--
                                now = next
                            }
                        }
                        appendLine(answer)
                    } catch (_: Exception) {
                        break
                    }
                }
            }.dropLast(1)
        )
    }
}