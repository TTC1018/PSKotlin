package baekjoon.binarysearch

class `1166` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, L, W, H) = readLine().split(" ").map(String::toLong)
        var (s, e) = 0.0 to minOf(L, W, H).toDouble()
        var answer = 0.0
        repeat(50) {
            val m = (s + e).div(2)
            val limit = (L / m).toLong() * (W / m).toLong() * (H / m).toLong()
            if (N <= limit) {
                s = m
                answer = m
            } else {
                e = m
            }
        }
        print(answer)
    }
}