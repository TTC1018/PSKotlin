package baekjoon.greedy

class `1263` {
    private data class Job(
        val T: Int,
        val S: Int,
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val J = List(N) { readLine().split(" ").map(String::toInt).run { Job(first(), last()) } }
            .sortedWith(compareBy({ it.S }, { it.T }))
            .reversed()
        var now = J.first().S
        for ((T, S) in J) {
            now = now.coerceAtMost(S)
            now -= T
        }

        if (now < -1) {
            print(-1)
        } else {
            print(now)
        }
    }
}