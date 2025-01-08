package baekjoon.string

class `29730` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        print(
            MutableList(N) { readLine() }.run {
                filterNot { it.startsWith("boj.kr") }
                    .sortedWith(compareBy({ it.length }, { it })) +
                        filter { it.startsWith("boj.kr/") }
                            .sortedBy { it.split("/").last().toInt() }
            }.joinToString("\n")
        )
    }
}