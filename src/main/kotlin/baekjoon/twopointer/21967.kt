package baekjoon.twopointer

class `21967` {
    fun main() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val A = readLine().split(" ").map(String::toInt)
        var (l, r) = 0 to 0
        val counter = mutableMapOf<Int, Int>().apply {
            this[A[r]] = 1
        }
        var answer = 0
        while (r < N) {
            val max = counter.keys.maxOf { it }
            val min = counter.keys.minOf { it }
            if (max - min <= 2) answer = answer.coerceAtLeast(r - l + 1)
            if (max - min > 2) {
                counter[A[l]] = counter.getOrDefault(A[l], 0) - 1
                if (counter[A[l]] == 0) counter.remove(A[l])
                l++
                if (l == r){
                    r++
                    takeIf { r < N }?.run {
                        counter[A[r]] = counter.getOrDefault(A[r], 0) + 1
                    }
                }
            } else {
                r++
                takeIf { r < N }?.run {
                    counter[A[r]] = counter.getOrDefault(A[r], 0) + 1
                }
            }
        }
        print(answer)
    }
}