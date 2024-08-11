package baekjoon.graph

class `24481` {
    private var N = 0
    private var M = 0
    private var R = 0
    private lateinit var B: Map<Int, MutableList<Int>>
    private lateinit var D: IntArray

    private fun dfs(now: Int, depth: Int) {
        B[now]?.forEach { next ->
            if (D[next] == -1) {
                D[next] = depth + 1
                dfs(next, depth + 1)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            N = first(); M = this[1]; R = last()
        }
        B = buildMap {
            repeat(M) {
                val (u, v) = readLine().split(" ").map { it.toInt() - 1 }
                getOrPut(u) { mutableListOf() }.add(v)
                getOrPut(v) { mutableListOf() }.add(u)
            }
            values.forEach { it.sort() }
        }
        D = IntArray(N) { -1 }.apply { this[R - 1] = 0 }
        dfs(R - 1, 0)
        print(D.joinToString("\n"))
    }
}