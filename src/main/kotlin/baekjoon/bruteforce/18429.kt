package baekjoon.bruteforce

class `18429` {
    private var N = 0
    private var K = 0
    private lateinit var A: List<Int>
    private var answer = 0
    private lateinit var V: BooleanArray

    private fun bruteforce(left: Int, cnt: Int) {
        if (cnt == N && left >= 500) {
            answer++
            return
        }
        if (left < 500) {
            return
        }

        for (i in 0 until N) {
            if (V[i].not()) {
                V[i] = true
                bruteforce(left + A[i] - K, cnt + 1)
                V[i] = false
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            N = first(); K = last()
        }
        A = readLine().split(" ").map(String::toInt)
        V = BooleanArray(N) { false }
        bruteforce(500, 0)
        print(answer)
    }
}