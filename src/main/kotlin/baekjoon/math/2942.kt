package baekjoon.math

import kotlin.math.sqrt

class `2942` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (R, G) = readLine().split(" ").map(String::toInt)
        val gcd = run {
            var (a, b) = maxOf(R, G) to minOf(R, G)
            while (b > 0) {
                a = b.run {
                    b = a % b
                    this
                }
            }
            a
        }
        // 모든 선수에게 같은 개수 줘야 함
        // 최대공약수의 약수 출력
        print(
            StringBuilder().apply {
                for (n in 1..sqrt(gcd.toDouble()).toInt()) {
                    if (gcd % n == 0) {
                        appendLine("$n ${R / n} ${G / n}")
                        if (n != gcd / n) {
                            appendLine("${gcd / n} ${R / (gcd / n)} ${G / (gcd / n)}")
                        }
                    }
                }
            }.dropLast(1)
        )
    }
}