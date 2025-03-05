package baekjoon.bipartitematching

class `1671` {
    private var N = 0
    private lateinit var S: List<Shark>
    private val V = mutableSetOf<Int>()
    private lateinit var answer: IntArray

    private data class Shark(
        val size: Int,
        val speed: Int,
        val intelli: Int,
    ) : Comparable<Shark> {
        override fun compareTo(other: Shark): Int {
            if (size == other.size && speed == other.speed && intelli == other.intelli) return 0
            if (size >= other.size && speed >= other.speed && intelli >= other.intelli) return 1
            else return -1
        }
    }

    private fun search(now: Int): Boolean {
        if (now in V) return false
        V += now

        for (i in S.indices) {
            if (now == i) continue
            if (S[now] > S[i] || (S[now] == S[i] && now < i)) {
                if (answer[i] == -1 || search(answer[i])) {
                    answer[i] = now
                    return true
                }
            }
        }
        return false
    }

    fun solution() {
        N = readln().toInt()
        S = List(N) { readln().split(" ").map(String::toInt).let { Shark(it[0], it[1], it[2]) } }
        answer = IntArray(N) { -1 }
        for (i in S.indices) {
            repeat(2) {
                V.clear()
                search(i)
            }
        }
        print(answer.count { it == -1 })
    }
}