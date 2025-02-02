package baekjoon.graph

class `28471` {
    private data class Pos(
        val x: Int,
        val y: Int,
    )

    private val d = listOf(
        Pos(1, -1), Pos(1, 1),
        Pos(0, -1), Pos(0, 1),
        Pos(-1, -1), Pos(-1, 0), Pos(-1, 1)
    )

    fun solution() {
        val N = readln().toInt()
        val B = List(N) { readln() }
        for (i in B.indices) {
            for (j in B[i].indices) {
                if (B[i][j] == 'F') {
                    val V = List(N) { BooleanArray(N) { false } }.apply {
                        this[i][j] = true
                    }

                    val q = ArrayDeque<Pos>().apply { add(Pos(i, j)) }
                    while (q.isNotEmpty()) {
                        val (x, y) = q.removeFirst()
                        d.forEach { (dx, dy) ->
                            val (nx, ny) = x + dx to y + dy
                            if (nx in 0 until N && ny in 0 until N) {
                                if (B[nx][ny] == '.' && V[nx][ny].not()) {
                                    V[nx][ny] = true
                                    q.add(Pos(nx, ny))
                                }
                            }
                        }
                    }
                    print(V.sumOf { it.count { it } } - 1)
                }
            }
        }
    }
}