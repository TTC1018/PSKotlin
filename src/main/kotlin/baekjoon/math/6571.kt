package baekjoon.math

import java.math.BigInteger

class `6571` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val f = buildList<BigInteger> {
            val limit = "1${"0".repeat(100)}".toBigInteger()
            add(BigInteger.ONE); add((2).toBigInteger())
            while (last() < limit) {
                add(this[lastIndex - 1] + this[lastIndex])
            }
        }

        val sb = StringBuilder()
        while (true) {
            val (a, b) = readLine().split(" ").map(String::toBigInteger)
            if (a == BigInteger.ZERO && b == BigInteger.ZERO) break

            sb.appendLine(f.count { it in a..b })
        }
        print(sb.dropLast(1))
    }
}