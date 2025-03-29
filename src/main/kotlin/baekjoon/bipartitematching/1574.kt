package baekjoon.bipartitematching

class `1574` {
    private var R = 0
    private var C = 0
    private var N = 0
    private val V = mutableSetOf<Int>()
    private lateinit var M: IntArray
    private lateinit var G: List<BooleanArray>

    private fun search(now: Int): Boolean {
        if (now in V) return false
        V += now

        (0 until C).forEach {
            if (G[now][it]) {
                if (M[it] == -1 || search(M[it])) {
                    M[it] = now
                    return true
                }
            }
        }
        return false
    }

    fun solution() {
        readln().split(" ").map(String::toInt).let {
            R = it[0]; C = it[1]; N = it[2]
        }
        M = IntArray(C) { -1 }
        G = List(R) { BooleanArray(C) { true } }.apply {
            repeat(N) {
                val (r, c) = readln().split(" ").map(String::toInt)
                this[r - 1][c - 1] = false
            }
        }
        (0 until R).forEach {
            V.clear()
            search(it)
        }
        print(M.count { it > -1 })
    }
}