package baekjoon.math

import kotlin.math.absoluteValue

class `23082` {
    private fun Int.generateNumber(): String {
        return StringBuilder().apply {
            var n = this@generateNumber
            while (n > 0) {
                append(n % 3)
                n /= 3
            }
        }.toString()
    }


    fun solution() {
        val N = readln().toInt()
        val absN = N.absoluteValue.generateNumber().toCharArray() + CharArray(1) { '0' }
        // 삼진법으로 바꾸면 2가 존재함
        // 2 -> 3^x * 2 -> (3-1) * 3^x -> 3^(x+1) - 3^x
        for (i in 0 until absN.size - 1) {
            when (absN[i]) {
                '3' -> {
                    absN[i + 1] = absN[i + 1] + 1
                    absN[i] = '0'
                }

                '2' -> {
                    absN[i + 1] = absN[i + 1] + 1
                    absN[i] = 'T'
                }
            }
        }
        val reverseMap = mapOf('1' to 'T', 'T' to '1')
        print(
            absN
                .reversed()
                .takeIf { N != 0 }
                ?.dropWhile { it == '0' }
                ?.joinToString("") { "${it.takeIf { N > 0 } ?: reverseMap.getOrElse(it) { '0' }}" }
                ?: 0
        )
    }
}