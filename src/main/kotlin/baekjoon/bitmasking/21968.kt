package baekjoon.bitmasking

class `21968` {
    private val Int.pow3 get() = (0 until this).fold(1L) { acc, n -> acc * 3 }

    fun solution() {
        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    val N = readln().toLong().toString(2).reversed()
                    appendLine(N.foldIndexed(0L) { i, acc, n -> acc + ((0L).takeIf { n == '0' } ?: i.pow3) })
                }
            }.dropLast(1)
        )
    }
}