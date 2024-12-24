package baekjoon.greedy

class `2777` {
    fun solution() = with(System.`in`.bufferedReader()) {
        // 2~9로 나눠서 답 도출
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val N = readLine().toInt()
            if (N == 1) {
                sb.appendLine(1)
            } else {
                var num = 9
                var left = N
                var cnt = 0
                while (left > 1 && num > 1) {
                    if (left % num == 0) {
                        left /= num
                        cnt++
                    } else {
                        num--
                    }
                }

                sb.appendLine(
                    if (left > 1) {
                        -1
                    } else {
                        cnt
                    }
                )
            }
        }
        print(sb.dropLast(1))
    }
}