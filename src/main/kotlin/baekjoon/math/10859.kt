package baekjoon.math

import kotlin.math.sqrt

class `10859` {
    private fun isPrime(x: Long): Boolean {
        if (x < 2) return false

        for (n in 2..sqrt(x.toDouble()).toLong()) {
            if (x % n == 0L) return false
        }
        return true
    }

    fun solution() {
        val N = readln()
        if ('3' in N || '4' in N || '7' in N) {
            print("no")
            return
        }

        val upsidedown = N.map {
            when (it) {
                '6' -> '9'
                '9' -> '6'
                else -> it
            }
        }.joinToString("").reversed().toLong()
        print("yes".takeIf { isPrime(N.toLong()) && isPrime(upsidedown) } ?: "no")
    }
}