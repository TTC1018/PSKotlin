package baekjoon.prefixsum

import java.util.StringTokenizer

class `32194` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val P = IntArray(N + 2).apply { this[1] = 1 }
        val sb = StringBuilder()
        repeat(N) {
            P[it + 2] += P[it + 1]
            val st = StringTokenizer(readLine())
            val op = st.nextToken()
            val x = st.nextToken().toInt() - 1
            val y = st.nextToken().toInt()
            val result = when (op) {
                "1" -> (P[y] - P[x]) == y - x
                else -> P[y] == P[x]
            }
            if (result) P[it + 2]++
            sb.appendLine("Yes".takeIf { result } ?: "No")
        }
        print(sb.dropLast(1))
    }
}