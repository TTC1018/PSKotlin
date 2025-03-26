package baekjoon.bipartitematching

class `11378` {
    private var N = 0
    private var M = 0
    private var K = 0
    private lateinit var matched: IntArray
    private lateinit var V: BooleanArray
    private lateinit var G: List<List<Int>>

    private fun search(now: Int): Boolean {
        if (V[now]) return false
        V[now] = true

        for (job in G[now]) {
            if (matched[job] == -1 || search(matched[job])) {
                matched[job] = now
                return true
            }
        }
        return false
    }

    fun solution() {
        readln().split(' ').map(String::toInt).let {
            N = it[0]; M = it[1]; K = it[2]
        }
        matched = IntArray(1000 + 1) { -1 }
        V = BooleanArray(1000 + 1)
        G = List(N) { readln().split(" ").drop(1).map { it.toInt() - 1 } }
        (0 until N).forEach {
            V.fill(false)
            search(it)
        }
        while (K > 0) {
            val prevK = K
            for (i in 0 until N) {
                V.fill(false)
                if (search(i)) K--
                if (K == 0) break
            }
            if (prevK == K) break
        }
        print(matched.count { it > -1 })
    }
}