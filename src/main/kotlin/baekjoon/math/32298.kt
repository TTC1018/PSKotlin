package baekjoon.math

class `32298` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        print((M * 2..(M * 2) + ((N - 1) * M) step M).joinToString(" "))
    }
}