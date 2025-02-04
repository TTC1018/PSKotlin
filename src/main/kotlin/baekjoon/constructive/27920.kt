package baekjoon.constructive

class `27920` {
    fun main() = with(System.`in`.bufferedReader()) {
        // 서로 계속 반대편으로 보내기
        val N = readLine().toInt()
        print(
            StringBuilder().apply {
                val end = (1).takeIf { N and 1 == 1 } ?: 2
                val start = (2).takeIf { N and 1 == 1 } ?: 1

                appendLine("YES")

                (N - 2 downTo end step 2).forEach { append(it).append(' ') }

                append(N).append(' ')

                (start until N step 2).forEach { append(it).append(' ') }

                appendLine()

                (0 until N).forEach {
                    append(
                        if (it and 1 == 1) it.div(2).plus(1)
                        else (N - it.div(2))
                    ).append(' ')
                }
            }
        )
    }
}