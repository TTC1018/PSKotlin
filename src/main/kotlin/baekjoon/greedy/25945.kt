package baekjoon.greedy

class `25945` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val a = readLine().split(" ").map(String::toInt)
        // 합을 n으로 나눈 값에 나머지를 여기 저기에 분산
        val target = a.sumOf { it }.floorDiv(n)
        var (up, down) = 0 to 0
        for (i in a.indices) {
            when {
                a[i] > target + 1 -> up += (a[i] - target - 1)
                a[i] < target -> down += target - a[i]
            }
        }
        print(up.coerceAtLeast(down))
    }
}