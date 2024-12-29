package baekjoon.greedy

class `17392` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map(String::toInt)
        val H = mutableListOf<Int>().apply {
            if (N > 0) addAll(readLine().split(" ").map(String::toInt))
        }
        val sumVal = H.sumOf { it + 1 }
        val left = (M - sumVal).coerceAtLeast(0)
        // 남는 게 있으면 최대한 분산시켜야됨
        //    OXXXOXXXXX
        //    OXXXOXXXOXX
        //    OXXXOXXXOXXO
        when {
            // 빈 날짜가 N개 이하면 약속 사이에 대충 끼워서 점수를 1만 잡아먹게 설정
            left <= N -> print(left)
            // 빈 날짜가 N개 초과면 약속 사이사이에 분산시킴
            left > N -> {
                val per = left.floorDiv(N + 1)
                val mod = left % (N + 1)
                val perSum = (1..per).sumOf { it * it }
                // 남은 나머지는 모든 곳에 한개씩 추가하는 걸로 분산시킴
                // 즉, 하루 더 연장되므로 점수 계산에서 (per+1)*(per+1)이 추가로 더해짐
                print((N + 1 - mod) * perSum + mod * (perSum + (per + 1) * (per + 1)))
            }
        }
    }
}