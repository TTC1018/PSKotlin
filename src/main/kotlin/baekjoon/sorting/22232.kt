package baekjoon.sorting

class `22232` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        val F = List(N) { readLine() }
        val E = List(M) { readLine() }.toSet()
        print(
            F.sortedWith(
                compareBy(
                    { it.split(".").first() },
                    { if (it.split(".").last() in E) -1 else 1 },
                    { it.split(".").last() }
                )
            ).joinToString("\n")
        )
    }
}