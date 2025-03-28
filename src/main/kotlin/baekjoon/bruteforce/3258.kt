package baekjoon.bruteforce

class `3258` {
    fun solution() {
        val (N, Z, M) = readln().split(" ").map(String::toInt)
        val B = readln().split(" ").map { it.toInt() - 1 }.toSet()

        val V = mutableSetOf<Int>()
        (1..N - 1).forEach {
            V.clear()
            var s = 0
            while (s !in V && s !in B) {
                V += s
                if (s == Z - 1) {
                    print(it)
                    return
                }
                s = (s + it) % N
            }
        }
    }
}