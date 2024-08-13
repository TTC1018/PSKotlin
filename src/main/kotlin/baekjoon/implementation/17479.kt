package baekjoon.implementation

class `17479` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (A, B, C) = readLine().split(" ").map(String::toInt)
        val menu1 = buildMap {
            repeat(A) {
                val (n, c) = readLine().split(" ")
                put(n, c.toInt())
            }
        }
        val menu2 = buildMap {
            repeat(B) {
                val (n, c) = readLine().split(" ")
                put(n, c.toInt())
            }
        }
        val menu3 = buildMap {
            repeat(C) {
                val n = readLine()
                put(n, 0)
            }
        }
        val N = readLine().toInt()
        val D = List(N) { readLine() }
        var total = 0L
        var menu1Total = 0L
        var menu2Flag = false
        var menu3Counter = 0
        var okay = true
        for (d in D) {
            total += menu1[d] ?: menu2[d] ?: menu3[d] ?: 0
            when (d) {
                in menu1 -> menu1Total += menu1[d]!!
                in menu2 -> menu2Flag = true
                in menu3 -> menu3Counter++
            }
        }

        if (menu1Total < 20000 && menu2Flag) okay = false
        if (total < 50000 && menu3Counter > 0) okay = false
        if (menu3Counter > 1) okay = false
        print(okay.run { if (this) "Okay" else "No" })
    }
}