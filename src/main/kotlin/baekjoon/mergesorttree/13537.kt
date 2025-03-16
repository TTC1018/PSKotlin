package baekjoon.mergesorttree

class `13537` {
    private var N = 0
    private var M = 0
    private lateinit var A: List<Int>
    private lateinit var tree: List<MutableList<Int>>

    private fun buildTree() {
        (0 until N).forEach {
            update(0, N - 1, it)
        }
        tree.forEach { it.sort() }
    }

    private fun update(s: Int, e: Int, target: Int, treeIdx: Int = 1) {
        if (target in s..e) {
            // 속하는 모든 범위에 추가
            tree[treeIdx] += A[target]

            if (s == e) return
            val m = (s + e) / 2
            val left = treeIdx shl 1
            val right = left + 1
            update(s, m, target, left)
            update(m + 1, e, target, right)
        }
    }

    private fun search(s: Int, e: Int, rs: Int, re: Int, k: Int, treeIdx: Int = 1): Int {
        if (re < s || e < rs) return 0
        if (rs <= s && e <= re) {
            return tree[treeIdx].run { size - binarySearch(k, this) }
        }

        val m = (s + e) / 2
        val left = treeIdx shl 1
        val right = left + 1
        return search(s, m, rs, re, k, left) + search(m + 1, e, rs, re, k, right)
    }

    private fun binarySearch(x: Int, arr: List<Int>): Int {
        var (s, e) = 0 to arr.size
        while (s < e) {
            val m = (s + e) / 2
            if (arr[m] <= x) {
                s = m + 1
            } else {
                e = m
            }
        }
        return e
    }

    fun solution() {
        N = readln().toInt()
        A = readln().split(" ").map(String::toInt)
        M = readln().toInt()
        tree = List(N shl 2) { mutableListOf() }
        buildTree()

        print(
            StringBuilder().apply {
                repeat(M) {
                    val (i, j, k) = readln().split(" ").map(String::toInt)
                    appendLine(search(0, N - 1, i - 1, j - 1, k))
                }
            }.dropLast(1)
        )
    }
}