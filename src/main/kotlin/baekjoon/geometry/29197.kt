package baekjoon.geometry

import kotlin.math.absoluteValue

class `29197` {
    private data class Pos(
        val x: Int,
        val y: Int,
    )

    private fun gcd(a: Int, b: Int): Int {
        var (x, y) = a to b
        while (y > 0) {
            x = y.let {
                y = x % y
                it
            }
        }
        return x
    }

    fun solution() {
        val N = readln().toInt()
        val answer = mutableSetOf<Pos>()
        val P = List(N) {
            readln().split(" ")
                .map(String::toInt)
                .run { Pos(first(), last()) }
        }
        for ((px, py) in P) {
            var (x, y) = px to py
            val gcd = gcd(x.absoluteValue, y.absoluteValue)
            val pos = Pos(x.div(gcd), y.div(gcd))
            answer += pos
        }
        print(answer.size)
    }
}