package baekjoon.greedy

class `31845` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var (N, M) = readLine().split(" ").map(String::toInt)
        val A = ArrayDeque<Int>().apply {
            addAll(readLine().split(" ").map(String::toInt).sortedDescending())
        }
        var answer = 0
        while (A.isNotEmpty() && M-- > 0) {
            val top = A.removeFirst()
            if (top < 0) {
                print(answer)
                return
            }
            answer += top
            A.removeLastOrNull()
        }
        print(answer)
    }
}