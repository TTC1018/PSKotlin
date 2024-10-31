package baekjoon.string

class `6616` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        while (true) {
            val N = readLine().toInt()
            if (N == 0) break
            val S = readLine().split(" ").joinToString("") { it.uppercase() }
            val limit = S.length
            val answer = CharArray(limit)
            var startIdx = 0
            var idx = 0
            S.forEach { s ->
                answer[idx] = s
                idx += N
                if (idx >= limit) {
                    idx = ++startIdx
                }
            }
            sb.appendLine(answer.joinToString(""))
        }
        print(sb.dropLast(1))
    }
}