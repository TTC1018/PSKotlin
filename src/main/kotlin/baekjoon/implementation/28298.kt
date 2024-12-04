package baekjoon.implementation

class `28298` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, K) = readLine().split(" ").map(String::toInt)
        val B = List(N) { readLine() }
        val counter = buildMap<Pair<Int, Int>, IntArray> {
            for (i in 0 until N step K) {
                for (j in 0 until M step K) {
                    for (k in 0 until K) {
                        for (l in 0 until K) {
                            val x = i + k
                            val y = j + l
                            val alpha = B[x][y]
                            getOrPut(k to l) { IntArray('Z' - 'A' + 1) }[alpha.code - 'A'.code]++
                        }
                    }
                }
            }
        }

        var answerCount = 0
        val tile = List(K) { CharArray(K) }.apply {
            counter.forEach { (x, y), v ->
                this[x][y] = v.run { 'A' + indexOfFirst { it == max() } }
            }
        }
        val answer = List(N) { CharArray(M) }.apply {
            for (i in 0 until N step K) {
                for (j in 0 until M step K) {
                    for (k in 0 until K) {
                        for (l in 0 until K) {
                            val x = i + k
                            val y = j + l
                            val alpha = B[x][y]
                            if (tile[k][l] != alpha) answerCount++
                            this[x][y] = tile[k][l]
                        }
                    }
                }
            }
        }

        println(answerCount)
        print(answer.joinToString("\n") { it.joinToString("") })
    }

}