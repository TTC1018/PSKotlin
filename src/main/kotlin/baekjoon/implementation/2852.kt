package baekjoon.implementation

class `2852` {
    private fun Int.toTime() = "${"${(this / 60)}".padStart(2, '0')}:${"${this % 60}".padStart(2, '0')}"

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val G = List(N) {
            readLine().split(" ")
                .run {
                    first().toInt() to
                            last().split(":")
                                .map(String::toInt)
                                .run { first() * 60 + last() }
                }
        }

        var (p1, p2) = 0 to 0
        var (a1, a2) = 0 to 0
        var prev = 0
        for ((t, s) in G) {
            val time = s - prev
            if (p1 > p2) {
                a1 += time
            } else if (p1 < p2) {
                a2 += time
            }
            prev = s

            when (t) {
                1 -> p1++
                2 -> p2++
            }
        }
        val time = 48 * 60 - prev
        if (p1 > p2) {
            a1 += time
        } else if (p1 < p2) {
            a2 += time
        }

        print("${a1.toTime()}\n${a2.toTime()}")
    }

}