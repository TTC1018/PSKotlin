package baekjoon.greedy

class `12845` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val L = readLine().split(" ").map(String::toLong)
        val maxVal = L.maxOf { it }
        print(L.sumOf { it + maxVal } - maxVal * 2)
    }
}