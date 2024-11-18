package baekjoon.hashmap

class `12867` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val M = readLine().toInt()
        val T = readLine().split(" ").map(String::toInt)
        val D = readLine().toList()
        val V = mutableSetOf<String>().apply { add("") }
        val counter = mutableMapOf<Int, Int>()
        T.zip(D) { t, d ->
            when (d) {
                '+' -> counter[t] = counter.getOrPut(t) { 0 } + 1
                '-' -> counter[t] = counter.getOrPut(t) { 0 } - 1
            }
            if (counter[t] == 0) counter.remove(t)

            val visitFlag = counter.entries.sortedBy { it.key }.joinToString("!") { "${it.key}:${it.value}" }
            if (visitFlag in V) {
                print(0)
                return@with
            }
            V.add(visitFlag)
        }
        print(1)
    }

}