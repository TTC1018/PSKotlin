package baekjoon.math

class `17427` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toLong()
        print((1L..N).map { N.floorDiv(it) * it }.sumOf { it })
    }
}