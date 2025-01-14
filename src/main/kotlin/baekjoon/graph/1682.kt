package baekjoon.graph

import kotlin.system.exitProcess

class `1682` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val G = readLine().split(" ")
            .chunked(4).run { first() + last().reversed() }.joinToString("")
        val q = ArrayDeque<Pair<String, Int>>().apply {
            add((IntArray(4) { it + 1 } + IntArray(4) { 8 - it }).joinToString("") to 0)
        }
        val V = mutableSetOf<String>().apply { add(q[0].first) }
        while (q.isNotEmpty()) {
            val (now, cnt) = q.removeFirst()
            if (now == G) {
                print(cnt)
                exitProcess(0)
            }

            var next = now.toCharArray()
            for (i in 0..3) {
                next[i + 4] = now[i]
                next[i] = now[i + 4]
            }
            var A = next.joinToString("")
            if (A !in V) {
                V += A
                q.add(A to cnt + 1)
            }

            next = now.toCharArray()
            for (i in 0..3) {
                next[(i + 1).mod(4)] = now[i]
                next[(i + 1).mod(4) + 4] = now[i + 4]
            }
            A = next.joinToString("")
            if (A !in V) {
                V += A
                q.add(A to cnt + 1)
            }

            next = now.toCharArray()
            next[1] = now[2]; next[5] = now[1]
            next[6] = now[5]; next[2] = now[6]
            A = next.joinToString("")
            if (A !in V) {
                V += A
                q.add(A to cnt + 1)
            }

            next = now.toCharArray()
            next[0] = now[7]; next[7] = now[0]
            A = next.joinToString("")
            if (A !in V) {
                V += A
                q.add(A to cnt + 1)
            }
        }
    }
}