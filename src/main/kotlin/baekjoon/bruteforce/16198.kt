package baekjoon.bruteforce

class `16198` {
    private var N = 0
    private lateinit var W: List<Int>
    private var V = 0
    private var answer = 0L

    private fun bruteforce(left: Int, total: Long = 0L) {
        if (left == 2) {
            answer = answer.coerceAtLeast(total)
            return
        }

        for (i in 1 until N - 1) {
            val bit = (1 shl i)
            if (V and bit == 0) {
                V = V or bit
                var l = -1
                var r = -1
                for (j in i + 1 until N) {
                    if (V and (1 shl j) == 0) {
                        r = j
                        break
                    }
                }
                for (j in i - 1 downTo 0) {
                    if (V and (1 shl j) == 0) {
                        l = j
                        break
                    }
                }
                bruteforce(left - 1, total + W[l] * W[r])
                V = V xor bit
            }
        }
    }

    fun solution() {
        N = readln().toInt()
        W = readln().split(' ').map(String::toInt)
        bruteforce(N)
        print(answer)
    }
}