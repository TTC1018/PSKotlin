package baekjoon.segment_tree

class `16975` {
    private var N = 0
    private lateinit var A: List<Long>
    private lateinit var tree: LongArray
    private var M = 0

    /**
     * 초기화 할 때 구간합을 기록하지 않기
     */
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
    }

    /**
     * 범위의 가장 부모가 되는 노드에만 기록
     */
    private fun update(s: Int, e: Int, ts: Int, te: Int, newValue: Long, treeIdx: Int = 1) {
        if (s > te || e < ts) return
        if (ts <= s && e <= te) {
            tree[treeIdx] += newValue
            return
        }

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        update(s, m, ts, te, newValue, left)
        update(m + 1, e, ts, te, newValue, right)
    }

    /**
     * - 출력해야 될 때 구간 합을 구함. 재귀하면서 숫자 값들을 누적
     * - 어차피 리프 노드만 값이 저장돼있어서 부모 노드들에는 추가할 값만 있음
     */
    private fun search(s: Int, e: Int, target: Int, total: Long = 0, treeIdx: Int = 1): Long {
        if (s > target || e < target) return 0
        if (s == e) return total + tree[treeIdx]

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        return search(s, m, target, total + tree[treeIdx], left) + search(
            m + 1,
            e,
            target,
            total + tree[treeIdx],
            right
        )
    }

    fun solution() {
        N = readln().toInt()
        A = readln().split(" ").map(String::toLong)
        tree = LongArray(N * 4)
        generateTree(0, N - 1)
        M = readln().toInt()
        val sb = StringBuilder()
        repeat(M) {
            val q = readln().split(" ").map(String::toInt)
            when (q[0]) {
                1 -> update(0, N - 1, q[1] - 1, q[2] - 1, q[3].toLong())
                2 -> sb.appendLine(search(0, N - 1, q[1] - 1))
            }
        }
        print(sb.dropLast(1))
    }
}