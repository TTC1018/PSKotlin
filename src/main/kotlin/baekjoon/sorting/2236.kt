package baekjoon.sorting

class `2236` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toInt)
        val B = readLine().split(" ").map(String::toInt).withIndex()
        val bSorted = B.sortedBy { it.value }
        val answer = IntArray(N).apply {
            for (i in bSorted.lastIndex downTo (bSorted.lastIndex - K + 1).coerceAtLeast(0)) {
                this[bSorted[i].index] = bSorted[i].index + 1
            }
        }
        println(B.mapIndexed { i, v -> if (answer[i] != 0) v.index + 1 else -1 }.filter { it >= 0 }
            .run { if (size < K) this + List(K - size) { 0 } else this }
            .joinToString("\n")
        )
        print(answer.joinToString("\n"))
    }
}