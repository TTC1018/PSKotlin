package baekjoon.bipartitematching

class `14433` {
    private var N = 0
    private var M = 0
    private var K1 = 0
    private var K2 = 0
    private lateinit var T1: List<MutableList<Int>>
    private lateinit var T2: List<MutableList<Int>>
    private val V = BooleanArray(300 + 1)
    private val C1 = IntArray(300 + 1) { -1 }
    private val C2 = IntArray(300 + 1) { -1 }

    private fun search(now: Int, t1: Boolean = true): Boolean {
        if (V[now]) return false
        V[now] = true

        val choice = (C1.takeIf { t1 } ?: C2)
        for (laptop in (T1.takeIf { t1 } ?: T2)[now]) {
            if (choice[laptop] == -1 || search(choice[laptop], t1)) {
                choice[laptop] = now
                return true
            }
        }

        return false
    }


    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = this[0]; M = this[1]; K1 = this[2]; K2 = last()
        }
        T1 = List(N) { mutableListOf<Int>() }.apply {
            repeat(K1) {
                val (i, j) = readln().split(" ").map { it.toInt() - 1 }
                this[i] += j
            }
        }
        T2 = List(N) { mutableListOf<Int>() }.apply {
            repeat(K2) {
                val (i, j) = readln().split(" ").map { it.toInt() - 1 }
                this[i] += j
            }
        }
        for (i in T1.indices) {
            V.fill(false)
            search(i)
        }
        for (i in T2.indices) {
            V.fill(false)
            search(i, false)
        }
        print("네 다음 힐딱이".takeIf { C1.count { it >= 0 } < C2.count { it >= 0 } } ?: "그만 알아보자")
    }
}