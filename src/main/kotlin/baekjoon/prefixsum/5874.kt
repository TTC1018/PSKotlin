package baekjoon.prefixsum

class `5874` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val C = readLine()
        val y = IntArray(C.length)
        for (i in 1 until C.length) {
            if (C[i - 1] == ')' && C[i] == ')') y[i - 1]++
            y[i] += y[i - 1]
        }
        var answer = 0
        for (i in 1 until C.length) {
            if (C[i - 1] == '(' && C[i] == '(') answer += (y.last() - y[i])
        }
        print(answer)
    }
}