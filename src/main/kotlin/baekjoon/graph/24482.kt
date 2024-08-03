package baekjoon.graph

class `24482` {
    private var N = 0
    private var M = 0
    private var R = 0
    private lateinit var G: Map<Int, MutableList<Int>>
    private lateinit var answer: IntArray

    private fun dfs(now: Int, depth: Int = 0) {
        G[now]?.forEach { next ->
            if (answer[next] == -1) {
                answer[next] = depth + 1
                dfs(next, depth + 1)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            N = first(); M = this[1]; R = last()
        }
        G = buildMap {
            repeat(M) {
                val (u, v) = readLine().split(" ").map(String::toInt)
                getOrPut(u) { mutableListOf() }.add(v)
                getOrPut(v) { mutableListOf() }.add(u)
            }
            keys.forEach { this[it]!!.sortDescending() }
        }
        answer = IntArray(N + 1) { -1 }.apply { this[R] = 0 }
        dfs(R)
        print(answer.drop(1).joinToString("\n"))
    }
}