package baekjoon.bruteforce

class `16508` {
    private lateinit var T: IntArray
    private var N = 0
    private lateinit var M: List<Int>
    private lateinit var C: List<IntArray>
    private var answer = Int.MAX_VALUE
    private var temp = 0
    private lateinit var counter: IntArray

    private fun bruteforce(idx: Int) {
        if (T.zip(counter).all { (a, b) -> a <= b }) {
            answer = answer.coerceAtMost(temp)
            return
        }
        if (idx == N) return

        for (i in idx until N) {
            temp += M[i]
            C[i].forEachIndexed { j, v -> counter[j] += v }
            bruteforce(i + 1)
            C[i].forEachIndexed { j, v -> counter[j] -= v }
            temp -= M[i]
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        T = readLine()
            .groupingBy { it }
            .eachCount()
            .let {
                IntArray('Z' - 'A' + 1).apply {
                    it.forEach { (k, v) -> this[k - 'A'] += v }
                }
            }
        N = readLine().toInt()
        counter = IntArray('Z' - 'A' + 1)
        val MC = List(N) { readLine().split(" ") }
        M = MC.map { it[0].toInt() }
        C = MC.map {
            it[1].groupingBy { s -> s }
                .eachCount()
                .let {
                    IntArray('Z' - 'A' + 1).apply {
                        it.forEach { (k, v) -> this[k - 'A'] += v }
                    }
                }
        }
        bruteforce(0)
        print(answer.takeIf { it < Int.MAX_VALUE } ?: -1)
    }

}