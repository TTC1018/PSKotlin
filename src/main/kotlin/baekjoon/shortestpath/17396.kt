package baekjoon.shortestpath

import java.util.*

class `17396` {
    private data class Route(
        val next: Int,
        val cost: Long,
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        val a = readLine().split(" ").map(String::toInt)
            .run { dropLast(1) + listOf(0) }
        val G = List(N) { mutableListOf<Route>() }.apply {
            repeat(M) {
                val (A, B, T) = readLine().split(" ").map(String::toInt)
                this[A].add(Route(B, T.toLong()))
                this[B].add(Route(A, T.toLong()))
            }
        }
        val D = LongArray(N) { Long.MAX_VALUE }.apply { this[0] = 0 }
        val q = PriorityQueue<Route>(compareBy { it.cost }).apply {
            add(Route(0, 0))
        }
        while (q.isNotEmpty()) {
            val (now, c) = q.remove()
            if (c > D[now]) continue // 더 긴 경로라면 패스
            if (now == N - 1) {
                print(c)
                return
            }

            for ((next, cost) in G[now]) {
                val nextCost = c + cost
                if (a[next] == 0 && nextCost < D[next]) {
                    D[next] = nextCost
                    q.add(Route(next, nextCost))
                }
            }
        }
        print(-1)
    }
}