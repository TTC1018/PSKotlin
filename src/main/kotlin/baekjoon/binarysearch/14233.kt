package baekjoon.binarysearch

class `14233` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().trim().toInt()
        val A = readLine().trim().split(" ").map(String::toLong).sorted()
        var answer = 1L
        var (l, r) = 1L to 1e9.toLong()
        loop@ while (l <= r) {
            val m = (l + r).div(2)
            var prev = 0L
            for (i in A.indices) {
                if (prev + m > A[i]) {
                    r = m - 1
                    continue@loop
                }
                prev += m
            }
            answer = m
            l = m + 1
        }
        print(answer)
    }
}