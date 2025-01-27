package baekjoon.math

class `1214` {
    fun solution() {
        val (D, P, Q) = readln().split(" ").map(String::toInt)
        val (s, b) = minOf(P, Q) to maxOf(P, Q)
        var left = 1e9.toInt()
        for (x in 0..D.floorDiv(b).coerceAtMost(s)) {
            val forS = (D - b * x).mod(s)
            if (forS == 0) {
                left = 0
                break
            } else {
                left = left.coerceAtMost((s - forS).mod(s))
            }
        }
        left = left.coerceAtMost((b - D.mod(b)).mod(b))
        print(D + left)
    }
}