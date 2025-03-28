package baekjoon.binarysearch

class `30685` {
    fun solution() {
        val N = readln().toInt()
        val B = List(N) { readln().split(" ").map(String::toInt) }
            .sortedBy { it[0] }
        // 버터가 녹으면 양 옆으로 퍼짐
        // 양 옆 버터가 가장 빠르게 닿음
        val maxVal = B.maxOf { it[1].floorDiv(2) }
        var answer = maxVal
        var (l, r) = 0 to maxVal
        while (l <= r) {
            val m = (l + r) / 2
            if (
                B.zipWithNext().any { (p, n) ->
                    p[0] + minOf(p[1].floorDiv(2), m) >= n[0] - minOf(n[1].floorDiv(2), m)
                }
            ) {
                r = m - 1
            } else {
                answer = m
                l = m + 1
            }
        }
        print(answer.takeIf { it != maxVal } ?: "forever")
    }
}