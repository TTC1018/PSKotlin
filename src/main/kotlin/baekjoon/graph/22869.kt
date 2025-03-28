package baekjoon.graph

import kotlin.math.absoluteValue

class `22869` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toInt)
        val A = readLine().split(" ").map(String::toInt)
        val V = BooleanArray(N) { false }
        val q = ArrayDeque<Int>().apply {
            add(0)
            V[0] = true
        }
        while (q.isNotEmpty()) {
            val i = q.removeFirst()
            if (i == N - 1) {
                print("YES")
                return
            }

            for (n in 1..K) {
                val j = i + n
                if (j == N) break
                if (V[j]) continue
                val energy = n * (1 + (A[j] - A[i]).absoluteValue)
                if (energy <= K) {
                    V[j] = true
                    q.addLast(j)
                }
            }
        }
        print("NO")
    }
}