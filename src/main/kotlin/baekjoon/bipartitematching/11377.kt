package baekjoon.bipartitematching

class `11377` {
    private var N = 0
    private var M = 0
    private var K = 0
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
            N = first(); M = this[1]; K = last()
        }
        W = List(N) { readln().split(" ").drop(1).map(String::toInt) }
        for (i in W.indices) {
            V.fill(false)
            search(i)
        }
        // K번 더 매칭되는지 확인
        for (i in W.indices) {
            V.fill(false)
            if (search(i)) {
                K--
                if (K == 0) {
                    print(aligned.count { it >= 0 })
                    return
                }
            }
        }
        print(aligned.count { it >= 0 })
    }
}