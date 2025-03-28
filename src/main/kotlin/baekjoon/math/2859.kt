package baekjoon.math

class `2859` {
    private val String.time
        get() = split(":").map(String::toInt).run {
            first() * 60 + last()
        }

    private val HH = 24
    private val MM = 60
    private val LIMIT = HH * MM

    private val days = listOf("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

    fun solution() {
        val T1 = readln().time
        val T2 = readln().time
        val P1 = readln().time
        val P2 = readln().time

        for (k in 0 until LIMIT) {
            var cand = T1 + P1 * k // 가능한 시간
            // T2가 따라올 수 있는 시간인지 확인
            if (cand >= T2 && (cand - T2) % P2 == 0) {
                val mm = cand % MM
                cand /= MM
                val hh = cand % HH
                cand /= HH

                println(days[cand % days.size])
                print("%02d:%02d".format(hh, mm))
                return
            }
        }
        print("Never")
    }
}