package baekjoon.bruteforce

import java.math.BigInteger

class `11747` {
    fun main() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val D = buildList {
            while (true) {
                try {
                    addAll(readLine().split(" "))
                } catch (e: Exception) {
                    break
                }
            }
        }.joinToString("")
        var answer = BigInteger.ZERO
        for (l in D.indices) {
            val perms = buildSet {
                for (j in 0 until N - l) {
                    add(D.slice(j..j + l))
                }
            }
            while ("$answer" in perms) {
                answer++
            }
            if ("$answer".length <= l) {
                break
            }
        }
        print(answer)
    }
}