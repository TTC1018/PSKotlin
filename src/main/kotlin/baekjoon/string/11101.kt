package baekjoon.string

class `11101` {
    fun solution() {
        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    val timeMap = readln().split(",").associate { it.split(":").run { first() to last().toInt() } }
                    val comb = readln().split("|").map { it.split("&").maxOf { timeMap[it]!! } }.minOf { it }
                    appendLine(comb)
                }
            }.dropLast(1)
        )
    }
}