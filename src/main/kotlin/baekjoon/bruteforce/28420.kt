package baekjoon.bruteforce

class `28420` {
    private lateinit var A: List<List<Int>>
    private var N = 0
    private var M = 0
    private var a = 0
    private var b = 0
    private var c = 0

    private fun check1(x: Int, y: Int): Int {
        var result = 0
        if (x + a > N || y + (b + c) > M) return Int.MAX_VALUE
        for (i in x until x + a) {
            for (j in y until y + (b + c)) {
                result += A[i][j]
            }
        }
        return result
    }

    private fun check2(x: Int, y: Int): Int {
        var result = 0
        if (x + (a + b) > N || y + (c + a) > M) return Int.MAX_VALUE
        for (i in x until x + a) {
            for (j in y until y + c) {
                result += A[i][j]
            }
        }
        for (i in x + a until (x + a) + b) {
            for (j in y + c until (y + c) + a) {
                result += A[i][j]
            }
        }
        return result
    }

    private fun check3(x: Int, y: Int): Int {
        var result = 0
        if (x + (a + c) > N || y + (b + a) > M) return Int.MAX_VALUE
        for (i in x until x + a) {
            for (j in y until y + b) {
                result += A[i][j]
            }
        }
        for (i in x + a until (x + a) + c) {
            for (j in y + b until (y + b) + a) {
                result += A[i][j]
            }
        }
        return result
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).let {
            N = it[0]; M = it[1]
        }
        readLine().split(" ").map(String::toInt).let {
            a = it[0]; b = it[1]; c = it[2]
        }
        A = List(N) { readLine().split(" ").map(String::toInt) }
        var answer = Int.MAX_VALUE
        for (i in A.indices) {
            for (j in A[i].indices) {
                answer = minOf(answer, check1(i, j), check2(i, j), check3(i, j))
            }
        }
        print(answer)
    }
}