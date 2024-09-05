package baekjoon.greedy

class `20365` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val S = readLine()
        var (b, r) = 0 to 0
        var prev = '-'
        for (s in S) {
            if (prev != s) {
                if (s == 'B') {
                    b++
                } else {
                    r++
                }
            }
            prev = s
        }
        print(minOf(b, r) + 1)
    }
}