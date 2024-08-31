package baekjoon.binarysearch

class `30459` {
    fun main() = with(System.`in`.bufferedReader()) {
        val (N, M, R) = readLine().split(" ").map(String::toInt)
        val A = readLine().split(" ").map(String::toInt)
            .sorted()
        val B = readLine().split(" ").map(String::toInt)
            .sorted()
        // 1. 말뚝 두개 고름
        // 2. 정중앙에 깃대 꽂음
        // 3. 밑변은 말뚝 간의 거리, 높이는 깃대의 높이 -> 삼각형
        // 현수막 넓이의 최댓값 = R
        // 현수막은 딱 하나만 설치
        // 밑변 길이 구하기 -> N^2 -> 4백만
        val base = buildSet {
            for (i in A.indices) {
                for (j in i + 1 until N) {
                    add((A[j] - A[i]).toDouble())
                }
            }
        }
        var answer = -1.0
        for (b in base) {
            var (l, r) = 0 to B.lastIndex
            while (l <= r) {
                val m = (l + r).div(2)
                val height = B[m]
                val area = b * height / 2
                if (area <= R) {
                    l = m + 1
                    answer = maxOf(answer, area)
                } else {
                    r = m - 1
                }
            }
        }
        if (answer == -1.0) {
            print(-1)
        } else {
            print("%.1f".format(answer))
        }
    }
}