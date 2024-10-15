package baekjoon.bitmasking

import java.math.BigInteger

class `4312` {
    fun main() = with(System.`in`.bufferedReader()) {
        val answer = mutableListOf<BigInteger>()
        val sb = StringBuilder()
        while (true) {
            answer.clear()
            val n = readLine().trim().toBigInteger().minus(BigInteger.ONE)
            if (n < BigInteger.ZERO) break
            if (n == BigInteger.ZERO) {
                sb.appendLine("{ }")
                continue
            }

            val bit = n.toString(2).reversed()
            var base = 0
            for (b in bit) {
                if (b == '1') {
                    answer.add("1${"0".repeat(base)}".toBigInteger(3))
                }
                base++
            }
            sb.appendLine("{ ${answer.joinToString(", ")} }")
        }
        print(sb.dropLast(1))
    }
}