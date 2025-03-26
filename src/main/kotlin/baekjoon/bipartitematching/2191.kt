package baekjoon.bipartitematching

class `2191` {
    private lateinit var matched: IntArray
    private lateinit var v: BooleanArray
    private lateinit var G: List<List<Int>>

    private fun search(now: Int): Boolean {
        if (v[now]) return false
        v[now] = true

        for (job in G[now]) {
            if (matched[job] == -1 || search(matched[job])) {
                matched[job] = now
                return true
            }
        }
        return false
    }

    fun solution() {
        val (N, M, S, V) = readln().split(' ').map(String::toInt)
        matched = IntArray(1000 + 1) { -1 }
        v = BooleanArray(1000 + 1)
        val R = List(N) { readln().split(" ").map(String::toFloat).map { it.times(1000).toLong() } }
        val H = List(M) { readln().split(" ").map(String::toFloat).map { it.times(1000).toLong() } }
        val SV = (S * V * 1000).toLong().let { it * it }
        G = List(N) { i ->
            val (rx, ry) = R[i]
            buildList {
                H.forEachIndexed { j, (x, y) ->
                    if ((rx - x).let { it * it } + (ry - y).let { it * it } <= SV) {
                        add(j)
                    }
                }
            }
        }
        (0 until N).forEach {
            v.fill(false)
            search(it)
        }
        print(N - matched.count { it > -1 })
    }
}