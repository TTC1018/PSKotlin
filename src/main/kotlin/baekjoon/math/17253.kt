package baekjoon.math

class `17253` {
    fun solution() = with(System.`in`.bufferedReader()) {
        print(
            if (readLine().toLong().toString(3).run { '1' in this && all { it == '0' || it == '1' } })
                "YES"
            else
                "NO"
        )
    }
}