package baekjoon.bruteforce

import kotlin.math.absoluteValue

class `14629` {
    private lateinit var N: String
    private var bit = 0
    private var answer = 9876543210

    private fun bruteforce(cand: String) {
        cand.toLongOrNull()?.let {
            val prev = (N.toLong() - answer).absoluteValue
            val diff = (N.toLong() - it).absoluteValue
            if (diff <= prev) {
                if (diff < prev) {
                    answer = it
                } else if (cand.toLong() < answer) {
                    answer = it
                }
            }
        }

        (0..9).forEach {
            if ((bit and (1 shl it)) == 0) {
                bit = bit or (1 shl it)
                bruteforce("$cand$it")
                bit = bit xor (1 shl it)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        N = readLine()
        bruteforce("")
        print(answer)
    }
}