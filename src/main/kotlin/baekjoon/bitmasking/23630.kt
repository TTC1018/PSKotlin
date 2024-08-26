package baekjoon.bitmasking

class `23630` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val A = readLine().split(' ').map(String::toInt)
        val counter = IntArray(20 + 1)
        for (a in A) {
            a.toString(2).reversed().forEachIndexed { i, v ->
                counter[i] += v.digitToInt()
            }
        }
        print(counter.maxOf { it })
    }
}