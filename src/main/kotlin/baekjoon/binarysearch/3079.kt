package baekjoon.binarysearch

class `3079` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toLong)
        val T = List(N.toInt()) { readLine().toLong() }
        var (l, r) = 1L to T.maxOf { it } * M
        var answer = r
        while (l <= r) {
            val m = (l + r).div(2)
            var cnt = 0L
            for (t in T) {
                cnt += m.floorDiv(t)
                if (cnt >= M)
                    break
            }

            if (cnt >= M) {
                r = m - 1
                answer = m
            } else {
                l = m + 1
            }
        }
        print(answer)
    }
}