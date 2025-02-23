package baekjoon.bruteforce

class `31287` {
    fun main() {
        var (N, K) = readln().split(" ").map(String::toInt)
        K = K.coerceAtMost(N)
        val S = readln()
        var (x, y) = 0 to 0
        repeat(K) {
            S.forEach { s ->
                when (s) {
                    'L' -> x--
                    'R' -> x++
                    'U' -> y++
                    'D' -> y--
                }
                if (x == 0 && y == 0) {
                    print("YES")
                    return
                }
            }
        }
        print("NO")
    }
}