package baekjoon.bruteforce

class `31801` {
    private val nums = BooleanArray(1e6.toInt() + 1) { false }
    private val P = IntArray(1e6.toInt() + 1)
    
    private val cand = StringBuilder()
    private fun bruteforce(prev: Int, cnt: Int, limit: Int, up: Boolean = true) {
        if (cnt == limit) {
            nums["$cand".toInt()] = true
            return
        }

        // 마지막 1개 이전까지는 증가해도 됨
        if (up && cnt + 1 < limit) {
            (prev + 1..9).forEach {
                cand.append(it)
                bruteforce(it, cnt + 1, limit)
                cand.deleteAt(cand.lastIndex)
            }
        }
        // 증가한 적 있으면 감소 가능
        if (up && cnt >= 2) {
            (prev - 1 downTo 0).forEach {
                cand.append(it)
                bruteforce(it, cnt + 1, limit, false)
                cand.deleteAt(cand.lastIndex)
            }
        }
        // 감소 중이면 감소
        if (up.not()) {
            (prev - 1 downTo 0).forEach {
                cand.append(it)
                bruteforce(it, cnt + 1, limit, false)
                cand.deleteAt(cand.lastIndex)
            }
        }
    }

    fun solution() {
        // 범위: 1 ~ 1000000
        (3..6).forEach { limit ->
            bruteforce(0, 0, limit)
        }
        for (n in 1..1e6.toInt()) {
            P[n] += P[n - 1] + ((1).takeIf { nums[n] } ?: 0)
        }

        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    val (a, b) = readln().split(" ").map(String::toInt)
                    appendLine(P[b] - P.getOrElse(a - 1) { 0 })
                }
            }.dropLast(1)
        )
    }
}