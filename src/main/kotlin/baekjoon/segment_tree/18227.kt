package baekjoon.segment_tree

class `18227` {
    private var N = 0
    private var C = 0
    private lateinit var G: List<MutableList<Int>>
    private lateinit var D: LongArray
    private lateinit var E: List<IntArray>
    private lateinit var tree: IntArray

    private var mark = 0
    private fun buildEuler(x: Int, depth: Long = 1) {
        if (E[x][0] > 0) return

        E[x][0] = ++mark
        D[x] = depth
        G[x].forEach { next -> buildEuler(next, depth + 1) }
        E[x][1] = mark
    }

    private fun rangeSum(s: Int, e: Int, rs: Int, re: Int, treeIdx: Int = 1): Int {
        if (e < rs || s > re) return 0
        if (rs <= s && e <= re) return tree[treeIdx]

        val m = (s + e) / 2
        val left = treeIdx shl 1
        val right = left + 1
        return rangeSum(s, m, rs, re, left) + rangeSum(m + 1, e, rs, re, right)
    }

    private fun update(s: Int, e: Int, target: Int, treeIdx: Int = 1, newValue: Int = 1) {
        if (target in s..e) {
            tree[treeIdx] += newValue
            if (s == e) return

            val m = (s + e) / 2
            val left = treeIdx shl 1
            val right = left + 1
            update(s, m, target, left)
            update(m + 1, e, target, right)
        }
    }

    fun solution() {
        readln().split(" ").map(String::toInt).run {
            N = first(); C = last()
        }
        G = List(N + 1) { mutableListOf<Int>() }.apply {
            repeat(N - 1) {
                val (x, y) = readln().split(" ").map(String::toInt)
                this[x] += y; this[y] += x
            }
        }
        D = LongArray(N + 1)
        E = List(N + 1) { IntArray(2) }
        tree = IntArray(N * 4)
        buildEuler(C)

        val Q = readln().toInt()
        print(
            StringBuilder().apply {
                repeat(Q) {
                    val (op, A) = readln().split(" ").map(String::toInt)
                    when (op) {
                        1 -> update(1, N, E[A][0])
                        2 -> appendLine(D[A] * rangeSum(1, N, E[A][0], E[A][1]))
                    }
                }
            }.dropLast(1)
        )

    }
}