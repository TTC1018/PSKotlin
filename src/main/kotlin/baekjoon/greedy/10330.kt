package baekjoon.greedy

class `10330` {
    fun main() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        val B = readLine().split(" ").toTypedArray().run {
            listOf(this, clone())
        }
        val C = readLine().split(" ").map(String::toInt)
        val T = List(2) {
            StringBuilder().apply {
                var b = "$it"
                for (c in C) {
                    append(b.repeat(c))
                    b = if (b == "0") "1" else "0"
                }
            }.toString().chunked(1)
        }
        
        val answer = IntArray(2)
        B.zip(T).forEachIndexed { n, BT ->
            val (b, t) = BT
            for (i in b.indices) {
                if (b[i] != t[i]) {
                    var swap = i
                    for (j in i + 1 until N) if (b[j] == t[i]) {
                        swap = j
                        break
                    }
                    if (swap == i) {
                        answer[n] = Int.MAX_VALUE
                        break
                    } else {
                        b[swap] = b[i]
                        b[i] = t[i]
                        answer[n] += (swap - i)
                    }
                }
            }
        }
        print(answer.minOf { it })
    }
}