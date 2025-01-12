package baekjoon.bruteforce

class `14223` {
    private data class Loc(
        val x: Long,
        val y: Long,
    )

    private var N = 0
    private lateinit var P: List<Loc>
    private lateinit var pSet: MutableSet<Loc>
    private var answer = Long.MAX_VALUE

    private fun bruteforce() {
        var maxX = Long.MIN_VALUE
        var maxY = Long.MIN_VALUE
        var minX = Long.MAX_VALUE
        var minY = Long.MAX_VALUE
        pSet.forEach { (x, y) ->
            maxX = maxOf(x, maxX); minX = minOf(x, minX)
            maxY = maxOf(y, maxY); minY = minOf(y, minY)
        }
        val w = (maxX - minX + 2)
        val h = (maxY - minY + 2)
        answer = minOf(answer, (maxOf(w, h).let { it * it }))
    }

    // 정사각형 내부에 N - 2개의 정사각형이 있어야함
    // 목표 좌표들에게서 +1 만큼 떨어지면 됨
    fun solution() = with(System.`in`.bufferedReader()) {
        N = readLine().toInt()
        P = List(N) { readLine().split(" ").map(String::toLong).let { Loc(it[0], it[1]) } }
        pSet = P.toMutableSet()
        for (i in P.indices) {
            for (j in i + 1 until N) {
                pSet.remove(P[i])
                pSet.remove(P[j])
                bruteforce()
                pSet.add(P[i])
                pSet.add(P[j])
            }
        }
        print(answer)
    }
}