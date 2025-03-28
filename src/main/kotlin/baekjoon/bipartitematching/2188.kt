package baekjoon.bipartitematching

class `2188` {
    private var N = 0
    private var M = 0
    private val V = mutableSetOf<Int>()
    private val entered = mutableMapOf<Int, Int>()
    private lateinit var C: List<List<Int>>

    private fun search(now: Int): Boolean {
        if (now in V) return false
        V += now

        for (cage in C[now]) {
            if (cage !in entered || search(entered[cage]!!)) {
                entered[cage] = now
                return true
            }
        }
        return false
    }

    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        C = List(N) { readln().split(" ").drop(1).map { it.toInt() - 1 } }
        (0 until N).forEach { V.clear(); search(it) }
        print(entered.size)
    }
}