package baekjoon.implementation

class `9555` {
    private data class Loc(
        val x: Int,
        val y: Int,
    )

    private val d = listOf(
        Loc(-1, -1), Loc(-1, 0), Loc(-1, 1),
        Loc(0, -1), Loc(0, 1),
        Loc(1, -1), Loc(1, 0), Loc(1, 1)
    )

    fun solution() {
        print(
            StringBuilder().apply {
                val checked = mutableSetOf<Int>()
                repeat(readln().toInt()) {
                    checked.clear()
                    val (N, M) = readln().split(" ").map(String::toInt)
                    val B = List(N) { readln().split(" ").map(String::toInt) }
                    for (i in B.indices) {
                        for (j in B[i].indices) {
                            if (B[i][j] != -1 && B[i][j] !in checked) {
                                for ((dx, dy) in d) {
                                    val (nx, ny) = i + dx to j + dy
                                    if (nx in 0 until N && ny in 0 until M) {
                                        if (B[nx][ny] == B[i][j]) {
                                            checked += B[i][j]
                                            break
                                        }
                                    }
                                }
                            }
                        }
                    }
                    appendLine(checked.size)
                }
            }.dropLast(1)
        )
    }
}