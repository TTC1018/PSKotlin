package baekjoon.stack

class `25918` {
    fun solution() {
        val N = readln().toInt()
        val S = readln()
        var answer = 0
        val stacks = List(2) { mutableListOf<Char>() }
        var temp = 0
        val idx = mapOf('(' to 0, ')' to 1)
        for (s in S) {
            stacks[idx[s]!!].takeIf { it.isNotEmpty() }
                ?.run {
                    removeLast()
                    temp--
                }
                ?: run {
                    stacks[(idx[s]!! + 1).mod(2)].add('(')
                    answer = answer.coerceAtLeast(++temp)
                }
        }
        print(answer.takeIf { stacks.all { it.isEmpty() } } ?: -1)
    }
}