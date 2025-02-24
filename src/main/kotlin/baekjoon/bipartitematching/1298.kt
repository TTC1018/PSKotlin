package baekjoon.bipartitematching

class `1298` {
    private var N = 0
    private var M = 0
    private lateinit var L: List<MutableList<Int>>
    private val V = BooleanArray(100 + 1)
    private val aligned = IntArray(100 + 1) { -1 }

    private fun search(now: Int): Boolean {
        if (V[now]) return false
        V[now] = true

        for (laptop in L[now]) {
            if (aligned[laptop] == -1 || search(aligned[laptop])) {
                aligned[laptop] = now
                return true
            }
        }

        return false
    }


    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        L = List<MutableList<Int>>(N) { mutableListOf() }.apply {
            repeat(M) {
                val (a, b) = readln().split(" ").map { it.toInt() - 1 }
                this[a] += b
            }
        }
        for (i in L.indices) {
            V.fill(false)
            search(i)
        }
        print(aligned.count { it >= 0 })
    }
}