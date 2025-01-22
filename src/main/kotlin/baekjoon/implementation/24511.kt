package baekjoon.implementation

class `24511` {
    fun solution() {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
        val B = readln().split(" ").map(String::toInt)
        val M = readln().toInt()
        val C = readln().split(" ").map(String::toInt)
        // 큐면 있던 애가 나가고, 스택이면 들어오는 애가 나감
        // 즉, 스택은 절대 안 바뀜
        print(
            B.filterIndexed { i, _ -> A[i] == 0 }
                .reversed()
                .run {
                    slice(0 until size.coerceAtMost(M)) +
                            ((M - size).takeIf { it > 0 }?.let { C.slice(0 until it) } ?: emptyList())
                }.joinToString(" ")
        )
    }
}