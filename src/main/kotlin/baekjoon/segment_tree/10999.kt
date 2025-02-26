package baekjoon.segment_tree

class `10999` {
    private var N = 0
    private var M = 0
    private var K = 0
    private lateinit var A: List<Long>
    private lateinit var tree: LongArray
    private lateinit var lazy: LongArray

    private fun generateTree(s: Int, e: Int, treeIdx: Int = 1) {
        if (s == e) {
            tree[treeIdx] = A[s]
            return
        }

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        generateTree(s, m, left)
        generateTree(m + 1, e, right)
        tree[treeIdx] = tree[left] + tree[right]
    }

    /**
     * - 누적한 값을 반영
     * - 현재 [treeIdx]가 가리키는 노드의 구간합을 업데이트 하고,
     * 자식들도 추후에 업데이트 하기 위해 [lazy]에 기록함
     * - 다음 노드에 넘겨진 기록된 값들은 접근됐을 때 반영됨
     */
    private fun propo(s: Int, e: Int, treeIdx: Int = 1) {
        if (lazy[treeIdx] == 0L) return
        tree[treeIdx] += (e - s + 1) * lazy[treeIdx]
        if (s != e) {
            val left = treeIdx * 2
            val right = treeIdx * 2 + 1
            lazy[left] += lazy[treeIdx]
            lazy[right] += lazy[treeIdx]
        }
        lazy[treeIdx] = 0
    }

    private fun update(s: Int, e: Int, ts: Long, te: Long, newValue: Long, treeIdx: Int = 1) {
        propo(s, e, treeIdx)
        if (s > te || e < ts) return
        if (ts <= s && e <= te) {
            lazy[treeIdx] += newValue
            propo(s, e, treeIdx)
            return
        }

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        update(s, m, ts, te, newValue, left)
        update(m + 1, e, ts, te, newValue, right)
        tree[treeIdx] = tree[left] + tree[right]
    }

    private fun rangeSum(s: Int, e: Int, rangeStart: Long, rangeEnd: Long, treeIdx: Int = 1): Long {
        propo(s, e, treeIdx)
        if (s > rangeEnd || e < rangeStart) return 0
        if (rangeStart <= s && e <= rangeEnd) return tree[treeIdx]

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        return rangeSum(
            s,
            m,
            rangeStart,
            rangeEnd,
            left
        ) + rangeSum(
            m + 1,
            e,
            rangeStart,
            rangeEnd,
            right
        )
    }

    fun solution() {
        readln().trim().split(" ").map(String::toInt).let {
            N = it[0]; M = it[1]; K = it[2]
        }
        A = List(N) { readln().toLong() }
        tree = LongArray(N * 4)
        lazy = LongArray(N * 4)
        generateTree(0, N - 1)
        val sb = StringBuilder()
        repeat(M + K) {
            val q = readln().trim().split(" ").map(String::toLong)
            when (q[0]) {
                1L -> update(0, N - 1, q[1] - 1, q[2] - 1, q[3])
                2L -> sb.appendLine(rangeSum(0, N - 1, q[1] - 1, q[2] - 1))
            }
        }
        print(sb.dropLast(1))
    }
}