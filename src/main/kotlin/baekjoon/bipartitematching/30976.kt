package baekjoon.bipartitematching

class `30976` {
    private var N = 0
    private var M = 0
    private val V = mutableSetOf<Int>()
    private val matched = mutableMapOf<Int, Int>()
    private lateinit var g: List<List<Int>>

    private fun search(now: Int): Boolean {
        if (now in V) return false
        V += now

        for (partner in g[now]) {
            if (partner !in matched || search(matched[partner]!!)) {
                matched[partner] = now
                return true
            }
        }
        return false
    }

    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        val G = readln().split(" ").map(String::toInt)
        val B = readln().split(" ").map(String::toInt)
        val L = readln().split(" ").map(String::toInt)
        val U = readln().split(" ").map(String::toInt)
        g = L.mapIndexed { i, l ->
            B.withIndex().filter { (bi, v) -> v < l && G[i] > U[bi] }.map { it.index + 400 }
        }
        G.forEachIndexed { i, _ ->
            V.clear()
            search(i)
        }
        print(matched.size)
    }
}