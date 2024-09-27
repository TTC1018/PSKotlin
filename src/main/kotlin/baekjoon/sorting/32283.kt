package baekjoon.sorting

import kotlin.math.pow

class `32283` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val S = readLine()
        val nums = buildList {
            for (n in 0 until (2.0).pow(N).toInt()) {
                add(n.toString(2).padStart(N, '0'))
            }
        }.sortedWith(
            compareBy(
                { it.count { it == '1' } },
                { it.reversed().toInt(2) }
            )
        )
        print(nums.indexOf(S))
    }
}