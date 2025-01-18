package baekjoon.greedy

class `32867` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toInt)
        val a = readLine().split(" ").map(String::toInt)

        var minVal = a.first()
        var maxVal = a.first()
        var answer = 0
        for (ai in a) {
            val nextMin = minVal.coerceAtMost(ai)
            val nextMax = maxVal.coerceAtLeast(ai)

            if (nextMax - nextMin >= K) {
                answer++
                minVal = ai
                maxVal = ai
            } else {
                minVal = nextMin
                maxVal = nextMax
            }
        }
        print(answer)
    }
}