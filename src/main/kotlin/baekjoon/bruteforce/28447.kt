package baekjoon.bruteforce

class `28447` {
    private var N = 0
    private var K = 0
    private lateinit var B: List<List<Int>>
    private var answer = Int.MIN_VALUE
    private val cands = mutableListOf<Int>()

    private fun bruteforce(idx: Int) {
        if (cands.size == K) {
            var temp = 0
            for (i in cands.indices) {
                for (j in i + 1 until cands.size) {
                    temp += B[cands[i]][cands[j]]
                }
            }
            answer = answer.coerceAtLeast(temp)
            return
        }
        if (idx == N) return


        cands += idx
        bruteforce(idx + 1)
        cands.removeLast()

        bruteforce(idx + 1)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            N = first(); K = last()
        }
        B = List(N) { readLine().split(" ").map(String::toInt) }
        bruteforce(0)
        print(answer)
    }
}