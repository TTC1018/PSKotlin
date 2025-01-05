package baekjoon.bruteforce

class `27884` {
    private var n = 0
    private var m = 0
    private lateinit var S: List<String>
    private val MOD = 1e9.toInt() + 7
    private var answer = 0L
    private val cands = mutableListOf<Long>()

    private fun check(): Boolean {
        var maxLength = 0
        var temp = 1
        for (i in 1 until n) {
            if (cands[i - 1] != cands[i]) {
                temp++
            } else {
                maxLength = maxOf(maxLength, temp)
                temp = 1
            }
        }
        maxLength = maxOf(maxLength, temp)
        return maxLength == m
    }

    private fun bruteforce(idx: Int) {
        if (idx == n) {
            if (check()) {
                answer += cands.fold(1L) { acc, n -> (acc * n) % MOD }
                answer %= MOD
            }
            return
        }

        // 카운트
        cands.add(11)
        bruteforce(idx + 1)
        cands.removeLast()
        cands.add(5)
        bruteforce(idx + 1)
        cands.removeLast()
    }

    // 지상 or 지하 개수 세기
    // 지상은 5가지, 지하는 11가지
    // 2^20 = 백만
    fun main() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            n = first(); m = last()
        }
        S = List(n) { readLine() }
        bruteforce(0)
        print(answer)
    }
}