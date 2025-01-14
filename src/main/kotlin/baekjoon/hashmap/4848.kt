package baekjoon.hashmap

class `4848` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val numMap = buildMap {
            put(0, "{}")
            put(1, "{{}}")
            for (n in 2..15) {
                put(
                    n,
                    "{${
                        buildList {
                            (0..n - 1).forEach { add(this@buildMap[it]!!) }
                        }.joinToString(",")
                    }}"
                )
            }
        }
        val mirror = buildMap {
            for (n in 0..15) {
                put(numMap[n]!!, n)
            }
        }
        print(
            List(readLine().toInt()) {
                val (a, b) = with(mirror) { this[readLine()]!! to this[readLine()]!! }
                numMap[a + b]!!
            }.joinToString("\n")
        )
    }
}