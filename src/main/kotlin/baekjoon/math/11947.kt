package baekjoon.math

import kotlin.math.pow

class `11947` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val N = readLine()
            val nextRange = (10.0).pow(N.length).toLong()
            val midVal = nextRange.div(2)
            if (N.toLong() < midVal) {
                val answer = StringBuilder()
                val answer2 = StringBuilder()
                for (n in N) {
                    val limit = n.digitToInt()
                    answer.append(limit)
                    answer2.append(9 - limit)
                }
                val num = "$answer".toULong()
                val num2 = "$answer2".toULong()
                sb.appendLine(num * num2)
            } else {
                sb.appendLine(midVal * midVal.minus(1))
            }
        }
        print(sb.dropLast(1))
    }
}