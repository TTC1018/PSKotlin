package baekjoon.twopointer

class `30804` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val S = readLine().split(" ").map(String::toInt)
        var counter = mutableMapOf<Int, Int>()
        var (l, r) = 0 to 0
        var answer = 0
        while (r < N) {
            counter[S[r]] = counter.getOrDefault(S[r], 0) + 1
            if (counter.keys.size <= 2) {
                answer = answer.coerceAtLeast(r - l + 1)
            } else {
                while (counter.keys.size > 2) {
                    counter[S[l]] = counter.getOrDefault(S[l], 0) - 1
                    if (counter[S[l]] == 0) {
                        counter.remove(S[l])
                    }
                    l++
                }
            }
            r++
        }
        print(answer)
    }
}