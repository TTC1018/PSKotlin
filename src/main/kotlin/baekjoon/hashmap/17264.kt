package baekjoon.hashmap

class `17264` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, P) = readLine().split(" ").map(String::toInt)
        val (W, L, G) = readLine().split(" ").map(String::toInt)
        val H = buildMap {
            repeat(P) {
                val (PN, R) = readLine().split(" ")
                put(PN, R == "W")
            }
        }
        var answer = 0
        var iron = true
        repeat(N) {
            val PN = readLine()
            if (H.getOrDefault(PN, false)) {
                answer += W
            } else {
                answer = (answer - L).coerceAtLeast(0)
            }

            if (answer >= G) {
                iron = false
            }
        }

        if (iron) {
            print("I AM IRONMAN!!")
        } else {
            print("I AM NOT IRONMAN!!")
        }
    }
}