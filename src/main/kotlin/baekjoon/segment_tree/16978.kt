package baekjoon.segment_tree

class `16978` {
    private var N = 0
    private lateinit var A: List<Long>
    private lateinit var tree: LongArray
    private var M = 0
    private val q1 = mutableListOf<List<Int>>()
    private val q2 = ArrayDeque<Pair<Int, List<Int>>>()
    private val answer = mutableListOf<Pair<Int, Long>>()

    private fun generateTree(s: Int, e: Int, treeIdx: Int = 1): Long {
        if (s == e) {
            tree[treeIdx] = A[s]
            return tree[treeIdx]
        }

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        tree[treeIdx] = generateTree(s, m, left) + generateTree(m + 1, e, right)
        return tree[treeIdx]
    }

    private fun update(s: Int, e: Int, target: Int, newValue: Long, treeIdx: Int = 1) {
        if (target in s..e) {
            if (s == e) {
                tree[treeIdx] = newValue
                return
            }

            val m = (s + e) / 2
            val left = treeIdx * 2
            val right = treeIdx * 2 + 1
            update(s, m, target, newValue, left)
            update(m + 1, e, target, newValue, right)
            tree[treeIdx] = tree[left] + tree[right]
        }
    }

    private fun rangeSum(s: Int, e: Int, rangeStart: Int, rangeEnd: Int, treeIdx: Int = 1): Long {
        if (s > rangeEnd || e < rangeStart) return 0
        if (rangeStart <= s && e <= rangeEnd) return tree[treeIdx]

        val m = (s + e) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        return rangeSum(s, m, rangeStart, rangeEnd, left) + rangeSum(m + 1, e, rangeStart, rangeEnd, right)
    }

    fun solution() {
        N = readln().toInt()
        A = readln().split(" ").map(String::toLong)
        tree = LongArray(N * 4)
        generateTree(0, N - 1)
        M = readln().toInt()
        repeat(M) {
            val q = readln().split(" ").map(String::toInt)
            when (q[0]) {
                1 -> q1.add(q)
                2 -> q2.add(it to q)
            }
        }
        q2.sortBy { it.second[1] }
        while (q2.firstOrNull()?.second?.get(1) == 0) {
            q2.removeFirst().run {
                answer += first to second.let { rangeSum(0, N - 1, it[2] - 1, it[3] - 1) }
            }
        }
        q1.forEachIndexed { i, q ->
            update(0, N - 1, q[1] - 1, q[2].toLong())
            while (i == q2.firstOrNull()?.second?.get(1)?.minus(1)) {
                q2.removeFirst().run {
                    answer += first to second.let { rangeSum(0, N - 1, it[2] - 1, it[3] - 1) }
                }
            }
        }
        print(answer.also { it.sortBy { p -> p.first } }.joinToString("\n") { "${it.second}" })
    }
}