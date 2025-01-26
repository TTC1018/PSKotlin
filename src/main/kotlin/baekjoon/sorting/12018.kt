package baekjoon.sorting

class `12018` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var (n, m) = readLine().trim().split(" ").map(String::toInt)
        print(
            List(n) {
                val (P, L) = readLine().trim().split(" ").map(String::toInt)
                readLine()
                    .trim()
                    .split(" ")
                    .map(String::toInt)
                    .sortedDescending().run { getOrElse(L - 1) { 1 } }
            }.sorted()
                .count { om -> (m >= om).also { m -= om } }
        )
    }
}