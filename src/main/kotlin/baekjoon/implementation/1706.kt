package baekjoon.implementation

class `1706` {
    fun solution() {
        val (R, C) = readln().split(" ").map(String::toInt)
        val P = List(R) { readln() }
        val answer = mutableListOf<String>()
        for (i in 0 until R) {
            val temp = StringBuilder()
            for (j in 0 until C) {
                P[i][j].takeIf { it != '#' }
                    ?.run { temp.append(this) }
                    ?: run {
                        temp.takeIf { it.length > 1 }?.run { answer.add("$this") }
                        temp.clear()
                    }
            }
            temp.takeIf { it.length > 1 }?.run { answer.add("$this") }
        }
        for (j in 0 until C) {
            val temp = StringBuilder()
            for (i in 0 until R) {
                P[i][j].takeIf { it != '#' }
                    ?.run { temp.append(this) }
                    ?: run {
                        temp.takeIf { it.length > 1 }?.run { answer.add("$this") }
                        temp.clear()
                    }
            }
            temp.takeIf { it.length > 1 }?.run { answer.add("$this") }
        }
        print(answer.minOf { it })
    }
}