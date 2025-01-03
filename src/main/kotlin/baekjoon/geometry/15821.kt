package baekjoon.geometry

class `15821` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toInt)
        val F = List(N) { readLine(); readLine().split(" ").map(String::toLong).chunked(2).map { it[0] to it[1] } }
            .map { it.maxOf { (x, y) -> x * x + y * y } }
            .sorted()
        print("${F[K - 1]}.00")
    }
}