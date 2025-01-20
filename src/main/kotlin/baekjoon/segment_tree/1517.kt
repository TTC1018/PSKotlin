package baekjoon.segment_tree

class `1517` {
    private var N = 0
    private lateinit var A: List<Int>
    private lateinit var sa: Map<Int, List<Int>>
    private lateinit var tree: IntArray

    private fun update(target: Int, rangeStart: Int = 0, rangeEnd: Int = N - 1, treeIndex: Int = 1) {
        if (target in rangeStart..rangeEnd) {
            if (rangeStart == rangeEnd) {
                tree[treeIndex]++
                return
            }

            val mid = (rangeStart + rangeEnd) / 2
            val left = treeIndex * 2
            val right = treeIndex * 2 + 1
            update(target, rangeStart, mid, left)
            update(target, mid + 1, rangeEnd, right)
            tree[treeIndex] = tree[left] + tree[right]
        }
    }

    private fun search(
        rangeStart: Int,
        rangeEnd: Int,
        start: Int = 0,
        end: Int = N - 1,
        treeIdx: Int = 1
    ): Int {
        if (end < rangeStart || start > rangeEnd) return 0
        if (rangeStart <= start && end <= rangeEnd) return tree[treeIdx]

        val mid = (start + end) / 2
        val left = treeIdx * 2
        val right = treeIdx * 2 + 1
        return search(
            rangeStart,
            rangeEnd,
            start,
            mid,
            left
        ) + search(
            rangeStart,
            rangeEnd,
            mid + 1,
            end,
            right
        )
    }

    fun solution() {
        N = readln().toInt()
        A = readln().split(" ").map { it.toInt() }
        sa = A.withIndex()
            .groupBy { it.value }
            .toSortedMap() // 작은 수부터 트리에 등록되도록 정렬
            .mapValues { it.value.map { v -> v.index } }
        tree = IntArray(N * 4)

        var answer = 0L
        for (idxes in sa.values) {
            idxes.forEach { i ->
                if (i < A.lastIndex) {
                    // i+1 ~ N-1에 기록된 숫자 있는지 확인
                    answer += search(i + 1, A.lastIndex)
                }
            }
            // 트리에 기록하기
            idxes.forEach { i -> update(i) }
        }
        print(answer)
    }

}