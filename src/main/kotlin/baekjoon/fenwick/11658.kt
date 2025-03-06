package baekjoon.fenwick

class `11658` {
    private var N = 0
    private var M = 0
    private lateinit var A: List<IntArray>
    private lateinit var FT: List<IntArray>

    private fun buildFenwick() {
        (1..N).forEach { x ->
            (1..N).forEach { y ->
                updateFenwick(x, y, A[x - 1][y - 1])
            }
        }
    }

    private fun rangeSum(x: Int, y: Int): Int {
        var result = 0
        var i = x
        while (i > 0) {
            var j = y
            while (j > 0) {
                result += FT[i][j]
                j -= (j and -j)
            }
            i -= (i and -i)
        }
        return result
    }

    private fun rangeSum(x1: Int, y1: Int, x2: Int, y2: Int) =
        rangeSum(x2, y2) - rangeSum(x1 - 1, y2) - rangeSum(x2, y1 - 1) + rangeSum(x1 - 1, y1 - 1)

    /**
     * - 어떤 수의 음수는 2의 보수
     * - 2의 보수란, 비트를 반전시키고 1을 더한 값
     * - 이 2의 보수와 AND 연산을 하면 가장 우측 1비트(LSB)만 남음
     * - LSB를 더하면 자신을 포함하는 구간이 차례로 등장함
     * - 펜윅트리를 업데이트 한다는 것은, 이 구간들에 새로운 값을 더하거나 빼주는 것
     */
    private fun updateFenwick(x: Int, y: Int, newValue: Int) {
        var i = x
        while (i <= N) {
            var j = y
            while (j <= N) {
                FT[i][j] += newValue
                j += (j and -j)
            }
            i += (i and -i)
        }
    }

    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        A = List(N) { readln().split(" ").map(String::toInt).toIntArray() }
        FT = List(N + 1) { IntArray(N + 1) }
        buildFenwick()

        val sb = StringBuilder()
        repeat(M) {
            val q = readln().split(" ").map(String::toInt)
            when (q[0]) {
                0 -> {
                    val (x, y, c) = q.drop(1)
                    A[x - 1][y - 1].run {
                        A[x - 1][y - 1] = c
                        updateFenwick(x, y, c - this)
                    }
                }

                1 -> {
                    val (x1, y1, x2, y2) = q.drop(1)
                    sb.appendLine(rangeSum(x1, y1, x2, y2))
                }
            }
        }
        print(sb.dropLast(1))
    }
}