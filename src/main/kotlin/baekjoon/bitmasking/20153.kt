package baekjoon.bitmasking

class `20153` {
    fun solution() {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
        // 싹 다 xor 연산을 했을 때 홀수번 연산하면 비트가 살아남음
        var answer = A.fold(0) { acc, n -> acc xor n }
        print(A.maxOf { answer xor it }.coerceAtLeast(answer).let { "$it$it" })
    }
}