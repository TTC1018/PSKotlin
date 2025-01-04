package baekjoon.geometry

class `2477` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val K = readLine().toInt()
        val C = List(6) { readLine().split(" ").map(String::toInt) }
        val mx = C.maxOf { if (it[0] == 3 || it[0] == 4) it[1] else 0 }
        val my = C.maxOf { if (it[0] == 1 || it[0] == 2) it[1] else 0 }
        val CC = C + C
        for (i in 0 until 6 * 2 - 4) {
            if (CC[i][0] == CC[i + 2][0] && CC[i + 1][0] == CC[i + 3][0]) {
                print(K * (mx * my - CC[i + 1][1] * CC[i + 2][1]))
                return
            }
        }
    }
}