package baekjoon.sorting

class `31872` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toInt)
        val A = (listOf(0L) + readLine().split(" ").map(String::toLong).sorted())
            .zipWithNext { a, b -> b - a }
            .sortedDescending()
        print(A.drop(K.coerceAtMost(A.size)).sumOf { it })
    }
}