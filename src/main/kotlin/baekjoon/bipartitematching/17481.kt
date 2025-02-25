package baekjoon.bipartitematching

class `17481` {
    private var N = 0
    private var M = 0
    private lateinit var names: List<String>
    private lateinit var F: List<List<String>>
    private val V = BooleanArray(1000 + 1)
    private val aligned = mutableMapOf<String, Int>()

    private fun search(now: Int): Boolean {
        if (V[now]) return false
        V[now] = true

        for (name in F[now]) {
            if (name !in aligned || search(aligned[name]!!)) {
                aligned[name] = now
                return true
            }
        }

        return false
    }


    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        names = List(M) { readln() }
        F = List(N) { readln().split(" ").drop(1) }
        for (i in F.indices) {
            V.fill(false)
            search(i)
        }
        print("YES".takeIf { aligned.size == N } ?: "NO\n${aligned.size}")
    }
}