package baekjoon.math

class `29715` {
    fun factorial(n: Int): Long = (1..n).fold(1L) { acc, n -> acc * n }

    fun combination(n: Int, r: Int): Long {
        if (r > n || r < 0) return 0
        return factorial(n) / (factorial(r) * factorial(n - r))
    }

    fun solution() {
        val (N, M) = readln().split(" ").map(String::toInt)
        val (X, Y) = readln().split(" ").map(String::toInt)
        var fixed = 0 // 고정된 숫자 개수
        var hint = 0 // 고정은 아니지만 나오는 숫자
        repeat(M) {
            val (a, b) = readln().split(" ").map(String::toInt)
            if (a == 0) {
                hint++
            } else {
                fixed++
            }
        }
        val unused = N - fixed // 남은 숫자 개수
        val total = combination(9 - (fixed + hint), unused - hint) * factorial(unused)
        val wait = (total - 1).floorDiv(3).times(Y)
        print(total * X + wait)
    }
}