package baekjoon.bruteforce

class `28286` {
    private var N = 0
    private var K = 0
    private lateinit var answerOMR: IntArray
    private lateinit var OMR: IntArray
    private var answerVal = 0

    private fun push(idx: Int, omr: IntArray) {
        for (i in omr.lastIndex downTo idx + 1) {
            omr[i] = omr[i - 1]
        }
        omr[idx] = 0
    }

    private fun pull(idx: Int, omr: IntArray) {
        for (i in idx until omr.lastIndex) {
            omr[i] = omr[i + 1]
        }
        omr[omr.lastIndex] = 0
    }

    private fun bruteforce(cnt: Int, now: IntArray) {
        if (cnt == K) return

        var next: IntArray
        for (i in now.indices) {
            next = now.clone()
            push(i, next)
            answerVal = answerVal.coerceAtLeast(next.zip(answerOMR).count { (a, b) -> a == b })
            bruteforce(cnt + 1, next)
        }
        for (i in now.indices) {
            next = now.clone()
            pull(i, next)
            answerVal = answerVal.coerceAtLeast(next.zip(answerOMR).count { (a, b) -> a == b })
            bruteforce(cnt + 1, next)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        readLine().split(" ").map(String::toInt).run {
            N = first(); K = last()
        }
        answerOMR = readLine().split(" ").map(String::toInt).toIntArray()
        OMR = readLine().split(" ").map(String::toInt).toIntArray()
        answerVal = answerOMR.zip(OMR).count { (a, b) -> a == b }
        bruteforce(0, OMR)
        print(answerVal)
    }
}