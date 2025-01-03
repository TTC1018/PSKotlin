package baekjoon.hashmap

import java.util.StringTokenizer

class `2121` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val (A, B) = readLine().split(" ").map(String::toInt)
        val P = buildMap<Int, MutableSet<Int>> {
            repeat(N) {
                val st = StringTokenizer(readLine())
                getOrPut(st.nextToken().toInt()) { mutableSetOf() }.add(st.nextToken().toInt())
            }
        }
        var answer = 0
        P.forEach { (x, ys) ->
            ys.forEach { y ->
                if (y + B in ys && (x + A in P && P[x + A]?.contains(y + B) == true && P[x + A]?.contains(y) == true))
                    answer++
            }
        }
        print(answer)
    }
}