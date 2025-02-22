package baekjoon.hashmap

class `32201` {
    fun solution() {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
        val B = readln().split(' ').map(String::toInt)
        val indexer = buildMap {
            A.forEachIndexed { i, v ->
                put(v, i)
            }
        }.toMutableMap()
        var maxVal = Int.MIN_VALUE
        B.forEachIndexed { i, v ->
            val diff = indexer[v]!! - i
            maxVal = maxOf(maxVal, diff)
            indexer.put(v, diff)
        }
        print(B.filter { indexer[it]!! == maxVal }.joinToString(" "))
    }
}