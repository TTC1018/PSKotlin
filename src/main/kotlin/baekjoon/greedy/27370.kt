package baekjoon.greedy

import kotlin.math.absoluteValue

class `27370` {
    fun solution() = with(System.`in`.bufferedReader()) {
        print(
            buildList {
                repeat(readLine().toInt()) {
                    val (N, Pa, Pb) = readLine().split(" ").map(String::toLong)
                    val X = readLine().split(" ").map(String::toLong).sorted()
                    // 각자에게 가까운 집을 서로 처리하면 그게 최소
                    // A - B 서로 같은 거리가 되는 집을 누가 차지할지만 결정
                    var (a, b) = 0L to 0L
                    val later = mutableListOf<Long>()
                    for (i in X.indices) {
                        val toA = (Pa - X[i]).absoluteValue
                        val toB = (Pb - X[i]).absoluteValue
                        if (toA < toB) {
                            a += toA * 2
                        } else if (toA > toB) {
                            b += toB * 2
                        } else {
                            later.add(toA * 2)
                        }
                    }
                    later.forEach { n ->
                        if (a <= b) {
                            a += n
                        } else {
                            b += n
                        }
                    }
                    add("${a + b} ${(a - b).absoluteValue}")
                }
            }.joinToString("\n")
        )
    }
}