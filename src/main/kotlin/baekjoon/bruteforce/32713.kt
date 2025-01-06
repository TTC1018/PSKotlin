package baekjoon.bruteforce

class `32713` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, K) = readLine().split(" ").map(String::toInt)
        val A = readLine().split(" ").map(String::toInt)
        // 수 마다 연속 길이 기록하기
        var answer = 1
        val nums = A.distinct()
        for (n in nums) {
            var temp = 0
            var left = K
            var (l, r) = 0 to 0
            while (r < N) {
                when {
                    A[r] == n -> {
                        temp++
                        r++
                    }

                    left > 0 -> {
                        left--
                        r++
                    }

                    else -> {
                        answer = maxOf(answer, temp)

                        while (l < r && left == 0) {
                            if (A[l] != n) {
                                left++
                            } else {
                                temp--
                            }
                            l++
                        }
                    }
                }
            }
            answer = maxOf(answer, temp)
        }
        print(answer)
    }
}