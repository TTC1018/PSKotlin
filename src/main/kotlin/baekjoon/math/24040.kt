package baekjoon.math

class `24040` {
    fun solution() {
    // xy = N
    // 2(x + y) % 3 == 0
    // (3*a + 3*b + 0 or 3)
    // N = 9ab or 9ab+6a+3b+2 or 9ab+3a+6b+2
    print(
        StringBuilder().apply {
            repeat(readln().toInt()) {
                appendLine(
                    readln().toLong()
                        .takeIf { it % 9L == 0L || it % 3L == 2L }
                        ?.run { "TAK" } ?: "NIE"
                )
            }
        }.dropLast(1)
    )
}
}