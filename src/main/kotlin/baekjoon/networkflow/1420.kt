package baekjoon.networkflow

import kotlin.math.absoluteValue

class `1420` {
    private data class Pos(
        val x: Int,
        val y: Int,
    )

    private val d = listOf(
        Pos(1, 0), Pos(0, 1)
    )

    fun solution() {
        val (N, M) = readln().split(' ').map(String::toInt)
        val n = (N * M) shl 1
        var (source, sink) = 0 to 0
        lateinit var s: Pos
        lateinit var e: Pos
        var num = 0
        val B = List(N) { i ->
            readln().also {
                it.forEachIndexed { j, v ->
                    when (v) {
                        'K' -> {
                            s = Pos(i, j)
                            source = num + 1
                        }

                        'H' -> {
                            e = Pos(i, j)
                            sink = num
                        }
                    }
                    num += 2
                }
            }
        }
        if ((s.x - e.x).absoluteValue + (s.y - e.y).absoluteValue == 1) {
            print(-1)
            return
        }

        val CP = List(n) { mutableMapOf<Int, Int>() }
        val F = List(n) { mutableMapOf<Int, Int>() }
        val G = mutableMapOf<Int, MutableList<Int>>().apply {
            (0 until N * M).forEach {
                val (i, o) = it * 2 to it * 2 + 1
                getOrPut(i) { mutableListOf() } += o
                getOrPut(o) { mutableListOf() } += i
                CP[i][o] = 1
                CP[o][i] = 0
                F[i][o] = 0
                F[o][i] = 0
            }

            var now = 0
            repeat(N) { i ->
                repeat(M) { j ->
                    if (B[i][j] != '#') {
                        d.forEachIndexed { di, (dx, dy) ->
                            val (nx, ny) = i + dx to j + dy
                            if (nx in 0 until N && ny in 0 until M) {
                                if (B[nx][ny] != '#') {
                                    val b = now + ((2 * M).takeIf { di == 0 } ?: 2)
                                    val (ai, ao) = now to now + 1
                                    val (bi, bo) = b to b + 1
                                    getOrPut(ao) { mutableListOf() } += bi
                                    getOrPut(bi) { mutableListOf() } += ao
                                    CP[ao][bi] = 1e9.toInt()
                                    CP[bi][ao] = 0
                                    F[ao][bi] = 0
                                    F[bi][ao] = 0

                                    getOrPut(bo) { mutableListOf() } += ai
                                    getOrPut(ai) { mutableListOf() } += bo
                                    CP[bo][ai] = 1e9.toInt()
                                    CP[ai][bo] = 0
                                    F[bo][ai] = 0
                                    F[ai][bo] = 0
                                }
                            }
                        }
                    }
                    now += 2
                }
            }
        }

        var answer = 0
        while (true) {
            val prev = IntArray(n) { -1 }
            val q = ArrayDeque<Int>().apply {
                this += source
            }
            while (q.isNotEmpty()) {
                val now = q.removeFirst()
                G[now]?.forEach { next ->
                    if (prev[next] == -1 && CP[now][next]!! > F[now][next]!!) {
                        q += next
                        prev[next] = now
                    }
                }
            }

            if (prev[sink] == -1) break
            var now = sink
            while (now != source) {
                val next = prev[now]
                F[next][now] = F[next].getOrPut(now) { 0 } + 1
                F[now][next] = F[now].getOrPut(next) { 0 } - 1
                now = next
            }
            answer++
        }
        print(answer)
    }
}