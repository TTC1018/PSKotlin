package baekjoon.binarysearch

class `11663` {
    private fun binarySearch(target: Int, upper: Boolean = false): Int {
        var (l, r) = 0 to x.lastIndex
        while (l <= r) {
            val m = (l + r) / 2
            when {
                x[m] == target -> return m
                x[m] > target -> r = m - 1
                x[m] < target -> l = m + 1
            }
        }
        return if (upper) r else l
    }

    private lateinit var x: List<Int>

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        x = readLine().split(" ").map(String::toInt).sorted()
        val sb = StringBuilder()
        repeat(M) {
            val (a, b) = readLine().split(" ").map(String::toInt)
            sb.appendLine(binarySearch(b, true) - binarySearch(a) + 1)
        }
        print(sb.dropLast(1))
    }
}