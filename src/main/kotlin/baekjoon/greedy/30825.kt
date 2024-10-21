package baekjoon.greedy

class `30825` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toLong)
        val A = readLine().split(" ").map(String::toLong).toLongArray()
        var answer = 0L
        for (i in 1..A.lastIndex) {
            val prevNext = A[i - 1] + K
            if (prevNext >= A[i]) {
                answer += (prevNext - A[i])
                A[i] = prevNext
            } else {
                answer += (A[i] - K - A[i - 1]) * i
            }
        }
        print(answer)
    }
}