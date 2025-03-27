package baekjoon.bipartitematching

class `1867` {
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
        val (n, k) = readln().split(' ').map(String::toInt)
        matched = IntArray(n + 1) { -1 }
        v = BooleanArray(n + 1)
        G = List<MutableList<Int>>(n) { mutableListOf() }.apply {
            repeat(k) {
                // 행과 열로 이분그래프 생성
                // 한 행을 지나가면 다른 행은 영향 받지 않음. 열만 영향 받음
                val (r, c) = readln().split(" ").map { it.toInt() - 1 }
                this[r] += c
            }
        }
        (0 until n).forEach {
            v.fill(false)
            search(it)
        }
        print(matched.count { it > -1 })
    }
}