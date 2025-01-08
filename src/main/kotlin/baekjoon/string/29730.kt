package baekjoon.string

class `29730` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        print(
            List(N) { readLine() }
                .partition { it.startsWith("boj.kr").not() }
                .run {
                    first.sortedWith(compareBy({ it.length }, { it })) +
                            second.sortedBy { it.split("/").last().toInt() }
                }.joinToString("\n")
        )
    }
}