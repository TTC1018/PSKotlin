package baekjoon.prefixsum

class `32173` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val s = readLine().split(" ").map(String::toLong)
        val P = LongArray(N).apply {
            this[0] = s.first()
            for (i in 1 until N) {
                this[i] += this[i - 1] + s[i]
            }
        }.toList()
        print(s.zip(P).maxOf { (si, p) -> si - (p - si) })
    }
}