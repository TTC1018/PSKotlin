package baekjoon.segment_tree

class `12846` {
    private var N = 0
    private lateinit var T: List<Long>
    private lateinit var tree: IntArray

    private fun generateTree(nowStart: Int, nowEnd: Int, treeIdx: Int = 1): Int {
        if (nowStart == nowEnd) {
            tree[treeIdx] = nowStart
            return tree[treeIdx]
        }

        val mid = (nowStart + nowEnd) / 2
        val leftIndex = treeIdx * 2
        val rightIndex = treeIdx * 2 + 1
        val leftMinIndex = generateTree(nowStart, mid, treeIdx = leftIndex)
        val rightMinIndex = generateTree(mid + 1, nowEnd, treeIdx = rightIndex)
        tree[treeIdx] = if (T[leftMinIndex] <= T[rightMinIndex]) {
            leftMinIndex
        } else {
            rightMinIndex
        }
        return tree[treeIdx]
    }

    private fun rangeSearch(
        rangeStart: Int,
        rangeEnd: Int,
        nowStart: Int = 0,
        nowEnd: Int = N - 1,
        treeIdx: Int = 1
    ): Int {
        if (nowStart > rangeEnd || nowEnd < rangeStart) return -1
        if (rangeStart <= nowStart && nowEnd <= rangeEnd) {
            return tree[treeIdx]
        }

        val mid = (nowStart + nowEnd) / 2
        val leftIndex = treeIdx * 2
        val rightIndex = treeIdx * 2 + 1
        val leftMinIndex = rangeSearch(rangeStart, rangeEnd, nowStart, mid, leftIndex)
        val rightMinIndex = rangeSearch(rangeStart, rangeEnd, mid + 1, nowEnd, rightIndex)
        if (leftMinIndex == -1) return rightMinIndex
        if (rightMinIndex == -1) return leftMinIndex

        return if (T[leftMinIndex] <= T[rightMinIndex]) {
            leftMinIndex
        } else {
            rightMinIndex
        }
    }

    private fun calcMax(start: Int, end: Int): Long {
        val minIdx = rangeSearch(start, end)
        return maxOf(
            T[minIdx] * (end - start + 1),
            (minIdx - 1).takeIf { start <= it }?.let { calcMax(start, it) } ?: 0,
            (minIdx + 1).takeIf { it <= end }?.let { calcMax(it, end) } ?: 0,
        )
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        N = readLine().toInt()
        T = readLine().split(" ").map(String::toLong)
        tree = IntArray(N * 4)
        generateTree(0, N - 1)
        print(calcMax(0, N - 1))
    }
}