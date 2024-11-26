package baekjoon.sorting

class `29198` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, K) = readLine().split(" ").map(String::toInt)
        val S = List(N) { readLine().toCharArray().sorted() }
            .sortedBy { it.joinToString("") }
        print(
            S.slice(0 until K)
                .flatten()
                .sorted()
                .joinToString("")
        )
    }
}