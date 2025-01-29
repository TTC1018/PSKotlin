package baekjoon.constructive

class `28356` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        if (N == 1 && M == 1) {
            print("1\n1")
        } else if (N == 1 || M == 1) {
            println(2)
            print(List(N) { i -> List(M) { (i + it).mod(2) + 1 } }.joinToString("\n") { it.joinToString(" ") })
        } else {
            println(4)
            print(List(N) { i -> List(M) { i.mod(2) * 2 + it.mod(2) + 1 } }.joinToString("\n") { it.joinToString(" ") })
        }
    }
}