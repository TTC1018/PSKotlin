package baekjoon.implementation

class `16569` {
    private data class Loc(
        val x: Int,
        val y: Int,
    )

    private data class Nova(
        val x: Int,
        val y: Int,
        val t: Int,
    )

    private val d = listOf(
        Loc(-1, 0), Loc(1, 0), Loc(0, -1), Loc(0, 1)
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val (M, N, V) = readLine().split(" ").map(String::toInt)
        val inRange: (Int, Int) -> Boolean = { a, b -> a in 0 until M && b in 0 until N }
        val (X, Y) = readLine().split(" ").map { it.toInt() - 1 }
        val h = List(M) { readLine().split(" ").map(String::toInt) }
        val visited = List(M) { BooleanArray(N) { false } }
        val v = List(M) { BooleanArray(N) { false } }
        val novas = ArrayDeque<Nova>().apply {
            repeat(V) {
                val (x, y, t) = readLine().split(" ").map(String::toInt)
                v[x - 1][y - 1] = true
                if (t == 0) {
                    d.forEach { (dx, dy) ->
                        val (nx, ny) = x - 1 + dx to y - 1 + dy
                        if (inRange(nx, ny)) {
                            visited[nx][ny] = true
                            add(Nova(nx, ny, 1))
                        }
                    }
                } else {
                    add(Nova(x - 1, y - 1, t))
                }
            }
        }

        var answerHeight = h[X][Y]
        var answerCost = 0
        val q = ArrayDeque<Loc>().apply {
            visited[X][Y] = true
            add(Loc(X, Y))
        }
        var time = 0
        while (q.isNotEmpty()) {
            time++
            val nexts = mutableListOf<Loc>()
            while (q.isNotEmpty()) {
                val (x, y) = q.removeFirst()
                d.forEach { (dx, dy) ->
                    val (nx, ny) = x + dx to y + dy
                    if (inRange(nx, ny) && visited[nx][ny].not() && v[nx][ny].not()) {
                        visited[nx][ny] = true
                        nexts.add(Loc(nx, ny))
                        if (answerHeight < h[nx][ny]) {
                            answerHeight = h[nx][ny]
                            answerCost = time
                        }
                    }
                }
            }
            q.addAll(nexts)

            val nextNovas = mutableListOf<Nova>()
            while (novas.isNotEmpty()) {
                val (x, y, t) = novas.removeFirst()
                if (t == time) {
                    d.forEach { (dx, dy) ->
                        val (nx, ny) = x + dx to y + dy
                        if (inRange(nx, ny) && visited[nx][ny].not()) {
                            visited[nx][ny] = true
                            nextNovas.add(Nova(nx, ny, t + 1))
                        }
                    }
                } else {
                    nextNovas.add(Nova(x, y, t))
                }
            }
            novas.addAll(nextNovas)
        }
        print("$answerHeight $answerCost")
    }
}