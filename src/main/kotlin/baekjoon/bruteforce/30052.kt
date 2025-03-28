package baekjoon.bruteforce

class `30052` {
    fun solution() {
        val (N, M) = readln().split(" ").map(String::toInt)
        val D = readln().toInt()
        var answer = 0
        for (i in 0 until N) {
            for (j in 0 until M) {
                val d1 = i + j // 좌상단 꼭지점
                val d2 = i + (M - 1 - j) // 우상단 꼭지점
                val d3 = (N - 1 - i) + j // 좌하단 꼭지점
                val d4 = (N - 1 - i) + (M - 1 - j) // 우하단 꼭지점
                if (maxOf(d1, d2, d3, d4) < D) {
                    answer++
                }
            }
        }
        print(answer)
    }
}