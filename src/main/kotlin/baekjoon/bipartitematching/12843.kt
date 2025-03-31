package baekjoon.bipartitematching

class `12843` {
    private var n = 0
    private var m = 0
    private val V = mutableSetOf<Int>()
    private lateinit var T: CharArray
    private lateinit var matched: IntArray
    private lateinit var G: List<MutableList<Int>>

    private fun search(now: Int): Boolean {
        if (now in V) return false
        V += now

        G[now].forEach { next ->
            if (matched[next] == -1 || search(matched[next])) {
                matched[next] = now
                return true
            }
        }
        return false
    }

    fun solution() {
        readln().split(" ").map(String::toInt).run {
            n = first(); m = last()
        }
        T = CharArray(n)
        repeat(n) {
            val (num, type) = readln().split(" ")
            T[num.toInt() - 1] = type[0]
        }
        matched = IntArray(n) { -1 }
        G = List(n) { mutableListOf() }
        repeat(m) {
            val (a, b) = readln().split(" ").map { it.toInt() - 1 }
            if (T[a] == 'c') G[b] += a
            else G[a] += b
        }
        (0 until n).forEach {
            V.clear()
            search(it)
        }
        print(matched.count { it == -1 })
    }
}