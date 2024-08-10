package baekjoon.sorting

class `31870` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val A = readLine().split(" ").map(String::toInt)
        var (a1, a2) = 0 to 0
        // 오름차순 카운트
        var a = A.toIntArray()
        repeat(N) {
            for (i in 0 until N - 1) {
                if (a[i] > a[i + 1]) {
                    a1++
                    a[i + 1] = a[i].run {
                        a[i] = a[i + 1]
                        this
                    }
                }
            }
        }
        // 내림차순 카운트
        a = A.toIntArray()
        repeat(N) {
            for (i in 0 until N - 1) {
                if (a[i] < a[i + 1]) {
                    a2++
                    a[i + 1] = a[i].run {
                        a[i] = a[i + 1]
                        this
                    }
                }
            }
        }
        print(minOf(a1, a2 + 1))
    }
}