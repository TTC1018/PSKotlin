package baekjoon.bipartitematching

class `2414` {
    private lateinit var matched: IntArray
    private lateinit var v: BooleanArray
    private lateinit var G: List<MutableList<Int>>

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
        val (N, M) = readln().split(' ').map(String::toInt)
        matched = IntArray(N * M) { -1 }
        v = BooleanArray(N * M)
        val B = List(N) { readln() }
        val R = List(N) { IntArray(M) { -1 } }
        val C = List(N) { IntArray(M) { -1 } }
        G = List(N * M) { mutableListOf() }
        var (numR, numC) = 0 to 0
        for (i in 0 until N) {
            var prevH = false
            for (j in 0 until M) {
                if (B[i][j] == '*') {
                    prevH = true
                    R[i][j] = numR
                } else if (prevH) {
                    prevH = false
                    numR++
                }
            }
            if (prevH) numR++
        }
        for (j in 0 until M) {
            var prevH = false
            for (i in 0 until N) {
                if (B[i][j] == '*') {
                    prevH = true
                    C[i][j] = numC
                } else if (prevH) {
                    prevH = false
                    numC++
                }
            }
            if (prevH) numC++
        }
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (B[i][j] == '*') G[R[i][j]] += C[i][j]
            }
        }
        (0 until numR).forEach {
            v.fill(false)
            search(it)
        }
        print(matched.count { it > -1 })
    }
}