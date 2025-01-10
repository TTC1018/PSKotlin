package baekjoon.geometry

import kotlin.math.absoluteValue

class `14400` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val l = List(n) { readLine().split(" ").map(String::toLong) }
        val x = l.map { it[0] }.sorted()
        val y = l.map { it[1] }.sorted()
        val (mx, my) = x.run { this[size / 2] } to y.run { this[size / 2] }
        print(l.sumOf { (x, y) -> (mx - x).absoluteValue + (my - y).absoluteValue })
    }
}