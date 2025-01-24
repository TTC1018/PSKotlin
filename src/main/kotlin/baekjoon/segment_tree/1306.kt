package baekjoon.segment_tree

class `1306` {
    private var N = 0
    private var M = 0
    private lateinit var L: List<Int>
    private lateinit var tree: IntArray

    private fun generateTree(nowStart: Int, nowEnd: Int, treeIdx: Int = 1): Int {
        if (nowStart == nowEnd) {
            tree[treeIdx] = L[nowStart]
            return tree[treeIdx]
        }

        val mid = (nowStart + nowEnd) / 2
        val leftIndex = treeIdx * 2
        val rightIndex = treeIdx * 2 + 1
        tree[treeIdx] =
            maxOf(generateTree(nowStart, mid, treeIdx = leftIndex), generateTree(mid + 1, nowEnd, treeIdx = rightIndex))
        return tree[treeIdx]
    }

    private fun rangeSearch(
        rangeStart: Int,
        rangeEnd: Int,
        nowStart: Int = 0,
        nowEnd: Int = N - 1,
        treeIdx: Int = 1
    ): Int {
        if (nowStart > rangeEnd || nowEnd < rangeStart) return 0
        if (rangeStart <= nowStart && nowEnd <= rangeEnd) {
            return tree[treeIdx]
        }

        val mid = (nowStart + nowEnd) / 2
        val leftIndex = treeIdx * 2
        val rightIndex = treeIdx * 2 + 1
        return maxOf(
            rangeSearch(rangeStart, rangeEnd, nowStart, mid, leftIndex),
            rangeSearch(rangeStart, rangeEnd, mid + 1, nowEnd, rightIndex)
        )
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run { N = first(); M = last() }
        L = readLine().split(" ").map(String::toInt)
        tree = IntArray(N * 4)
        generateTree(0, N - 1)
        print((M - 1..N - M).map { rangeSearch(it - M + 1, it + M - 1) }.joinToString(" "))
    }
}