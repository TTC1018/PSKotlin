package baekjoon.fenwick

class `16404` {
    private var N = 0
    private var M = 0
    private lateinit var P: List<Int>
    private val G = mutableMapOf<Int, MutableList<Int>>()
    private lateinit var FT: IntArray
    private lateinit var tree: List<IntArray>

    var mark = 0

    /**
     * 오일러 경로 테크닉으로 각 노드가 갖는 구간합의 범위를 기록
     */
    private fun buildTree(treeIdx: Int = 1) {
        mark++
        tree[treeIdx][0] = mark
        G[treeIdx]?.forEach {
            buildTree(it)
        }
        tree[treeIdx][1] = mark
    }

    private fun update(now: Int, newValue: Int) {
        val (s, e) = tree[now]
        if (e == 0) return

        rangeUpdate(s, e, newValue)
    }

    /**
     * [[s], [e]] 구간을 변화시키는 것은
     * [FT][[s]]에 [newValue]를 추가하고, [FT][[e + 1]]에 [newValue]를 차감하는 것
     */
    private fun rangeUpdate(s: Int, e: Int, newValue: Int) {
        updateFenwick(s, newValue)
        updateFenwick(e + 1, -newValue)
    }

    /**
     * - 어떤 수의 음수는 2의 보수
     * - 2의 보수란, 비트를 반전시키고 1을 더한 값
     * - 이 2의 보수와 AND 연산을 하면 가장 우측 1비트(LSB)만 남음
     * - LSB를 더하면 자신을 포함하는 구간이 차례로 등장함
     * - 펜윅트리를 업데이트 한다는 것은, 이 구간들에 새로운 값을 더하거나 빼주는 것
     */
    private fun updateFenwick(now: Int, newValue: Int) {
        var x = now
        while (x <= N) {
            FT[x] += newValue
            x += (x and -x)
        }
    }

    /**
     * - LSB를 빼면 하위 구간을 탐색
     * - 계속해서 빼면서 값을 누적하여 부사수들의 잔고 합을 구하기
     */
    private fun check(now: Int): Int {
        var s = tree[now][0]
        var result = 0
        while (s > 0) {
            result += FT[s]
            s -= (s and -s)
        }
        return result
    }


    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); M = last()
        }
        P = readln().split(" ").map(String::toInt)
            .also {
                for (i in 1 until N) {
                    G.getOrPut(it[i]) { mutableListOf() } += (i + 1)
                }
            }
        tree = List(N + 1) { IntArray(2) }
        buildTree()
        FT = IntArray(N + 1)

        val sb = StringBuilder()
        repeat(M) {
            val q = readln().split(" ").map(String::toInt)
            when (q[0]) {
                1 -> update(q[1], q[2])
                2 -> sb.appendLine(check(q[1]))
            }
        }
        print(sb.dropLast(1))
    }
}