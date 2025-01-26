package baekjoon.segment_tree

class `1321` {
    private var N = 0
    private lateinit var M: List<Int>
    private lateinit var tree: IntArray
    private val answer = mutableListOf<Int>()

    private fun generateTree(
        rangeStart: Int,
        rangeEnd: Int,
        nowStart: Int = 0,
        nowEnd: Int = N - 1,
        treeIdx: Int = 1
    ): Int {
        if (nowStart == nowEnd) {
            tree[treeIdx] = M[nowStart]
            return tree[treeIdx]
        }

        val mid = (nowStart + nowEnd) / 2
        val leftIndex = treeIdx * 2
        val rightIndex = leftIndex + 1
        tree[treeIdx] =
            generateTree(rangeStart, rangeEnd, nowStart, mid, leftIndex) +
                    generateTree(rangeStart, rangeEnd, mid + 1, nowEnd, rightIndex)
        return tree[treeIdx]
    }

    private fun update(target: Int, newValue: Int, rangeStart: Int = 0, rangeEnd: Int = N - 1, treeIdx: Int = 1) {
        if (target in rangeStart..rangeEnd) {
            if (rangeStart == rangeEnd) {
                tree[treeIdx] += newValue
                return
            }

            val mid = (rangeStart + rangeEnd) / 2
            val leftIndex = treeIdx * 2
            val rightIndex = leftIndex + 1
            update(target, newValue, rangeStart, mid, leftIndex)
            update(target, newValue, mid + 1, rangeEnd, rightIndex)
            tree[treeIdx] = tree[leftIndex] + tree[rightIndex]
        }
    }

    private fun search(target: Int, rangeStart: Int = 0, rangeEnd: Int = N - 1, treeIdx: Int = 1) {
        if (rangeStart == rangeEnd) {
            answer += rangeStart + 1
            return
        }

        val mid = (rangeStart + rangeEnd) / 2
        val left = tree[treeIdx * 2]
        when {
            target < left -> search(target, rangeStart, mid, treeIdx * 2)
            else -> search(target - left, mid + 1, rangeEnd, treeIdx * 2 + 1)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        N = readLine().toInt()
        M = readLine().split(" ").map(String::toInt)
        tree = IntArray(N * 4)
        generateTree(0, N - 1)
        repeat(readLine().toInt()) {
            val ops = readLine().split(" ").map(String::toInt)
            when (ops.first()) {
                1 -> update(ops[1] - 1, ops[2])
                2 -> search(ops[1] - 1)
            }
        }
        print(answer.joinToString("\n"))
    }
}