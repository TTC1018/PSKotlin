package baekjoon.greedy

class `20012` {
    fun solution() {
        val n = readln().toInt()
        val a = readln().split(" ").map(String::toInt).toIntArray()
        val answer = mutableListOf<Int>()
        //
        for (i in 0 until n - 2) {
            if ((a[i] < a[i + 1] && a[i + 1] < a[i + 2]) || (a[i] > a[i + 1] && a[i + 1] > a[i + 2])) {
                a[i + 2] = a[i + 1].let {
                    a[i + 1] = a[i + 2]
                    it
                }
                answer += (i + 1) + 1
            }
        }
        print("${answer.size}${answer.takeIf { it.isNotEmpty() }?.joinToString(" ")?.let { "\n$it" } ?: ""}")
    }

}