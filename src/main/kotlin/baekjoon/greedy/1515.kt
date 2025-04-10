package baekjoon.greedy

class `1515` {
    fun solution() {
        val s = readln()
        var num = 0
        var sIdx = 0
        while (sIdx <= s.lastIndex) {
            num++
            "$num".forEach {
                if (it == s.getOrNull(sIdx)) {
                    sIdx++
                }
            }
        }
        print(num)
    }
}