package baekjoon.bipartitematching

class `11375` {
    private var N = 0
    private var M = 0
    private lateinit var W: List<List<Int>>
    private val V = BooleanArray(1000 + 1)
    private val aligned = mutableMapOf<Int, Int>()

    private fun search(now: Int): Boolean {
        if (V[now]) return false
        V[now] = true

        for (job in W[now]) {
            if (job !in aligned || search(aligned[job]!!)) {
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
        W.forEachIndexed { i, _ -> V.fill(false); search(i) }
        print(aligned.size)
    }
}