package baekjoon.segment_tree

class `1572` {
    private lateinit var data: List<Int>
    private lateinit var tree: IntArray // 0 ~ 65535 숫자 값 기록

    private fun update(target: Int, newValue: Int, start: Int, end: Int, treeIdx: Int = 1): Int {
        if (target < start || target > end) {
            return tree[treeIdx]
        }
        if (start == end) {
            tree[treeIdx] += newValue
            return tree[treeIdx]
        }

        val mid = (start + end) / 2
        val leftChildIdx = treeIdx * 2
        val rightChildIdx = treeIdx * 2 + 1
        tree[treeIdx] = update(
            target,
            newValue,
            start,
            mid,
            leftChildIdx
        ) + update(
            target,
            newValue,
            mid + 1,
            end,
            rightChildIdx
        )
        return tree[treeIdx]
    }

    private fun search(cnt: Int, start: Int, end: Int, treeIdx: Int = 1): Int {
        if (start == end) {
            return start
        }

        val mid = (start + end) / 2
        val leftChildIdx = treeIdx * 2
        val rightChildIdx = treeIdx * 2 + 1
        return if (cnt <= tree[leftChildIdx]) {
            search(cnt, start, mid, leftChildIdx)
        } else {
            search(cnt - tree[leftChildIdx], mid + 1, end, rightChildIdx)
        }
    }


    fun solution() {
        val (N, K) = readln().split(" ").map { it.toInt() }
        data = List(N) { readln().toInt() }
        tree = IntArray(65536 * 4)
        repeat(K - 1) { update(data[it], 1, 0, 65535) }
        val mid = (K + 1) / 2
        print(
            (K - 1 until N).map { i ->
                update(data[i], 1, 0, 65535)
                search(mid, 0, 65535).also {
                    update(data[i - (K - 1)], -1, 0, 65535)
                }
            }.sumOf(Int::toLong)
        )
    }
}