package baekjoon.tree

class `15805` {
    private var N = 0
    private lateinit var A: List<Int>
    private lateinit var answer: IntArray
    private lateinit var V: BooleanArray

    private fun search(i: Int) {
        if (i == A.lastIndex) return

        if (V[A[i + 1]].not()) {
            answer[A[i + 1]] = A[i]
            V[A[i + 1]] = true
        }
        search(i + 1)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        N = readLine().toInt()
        A = readLine().split(" ").map(String::toInt)
        val lastNum = A.maxOf { it }
        V = BooleanArray(lastNum + 1) { false }.apply { this[A.first()] = true }
        answer = IntArray(lastNum + 1) { -1 }
        search(0)
        print("${lastNum + 1}\n${answer.joinToString(" ")}")
    }
}