package baekjoon.string

class `2149` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val K = readLine()
        val S = readLine()
        val sK = K.withIndex().sortedWith(compareBy({ it.value }, { it.index }))
        val original = S.chunked(S.length / sK.size).zip(sK)
            .sortedBy { it.second.index }
            .map { it.first.toCharArray() }
        val answer = StringBuilder()
        for (j in 0 until original.first().size) {
            for (i in K.indices) {
                answer.append((original[i][j]))
            }
        }
        print(answer)
    }
}