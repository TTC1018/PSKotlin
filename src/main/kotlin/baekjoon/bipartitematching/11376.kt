package baekjoon.bipartitematching

class `11376` {
    private var N = 0
    private var M = 0
    private lateinit var W: List<List<Int>>
    private val V = BooleanArray(1000 + 1)
    private val aligned = IntArray(1000 + 1) { -1 }

    private fun search(now: Int): Boolean {
        if (V[now]) return false
        V[now] = true

        for (job in W[now]) {
            if (aligned[job] == -1 || search(aligned[job])) {
                aligned[job] = now
                return true
            }
        }

        return false
    }


    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        W = List(N) { readln().split(" ").drop(1).map(String::toInt) }
        for (i in W.indices) {
            repeat(2) {
                V.fill(false)
                search(i)
            }
        }
        print(aligned.count { it >= 0 })
    }
}