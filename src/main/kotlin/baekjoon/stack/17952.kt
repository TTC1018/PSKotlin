package baekjoon.stack

class `17952` {
    private data class Homework(
        val A: Int,
        val T: Int,
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val memo = mutableListOf<Homework>()
        var answer = 0L
        repeat(N) {
            val ops = readLine().split(" ").map(String::toInt)
            when (ops.first()) {
                0 -> {
                    if (memo.lastOrNull() != null) {
                        memo[memo.lastIndex] = memo[memo.lastIndex].run {
                            copy(T = T - 1)
                        }
                        if (memo[memo.lastIndex].T == 0) {
                            answer += memo.removeLast().A
                        }
                    }
                }
                1 -> {
                    val A = ops[1]
                    val T = ops[2]
                    memo.add(Homework(A, T - 1))
                    if (T - 1 == 0) {
                        answer += memo.removeLast().A
                    }
                }
            }
        }
        print(answer)
    }
}