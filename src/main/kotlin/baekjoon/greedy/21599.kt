package baekjoon.greedy

class `21599` {
    fun solution() {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
            .sortedDescending()
        // 큰 것부터 앞에 배치해야 중복 강화가 많아짐
        var answer = 0
        for (i in A.indices) {
            // A[i] != 0이라면 어차피 이전까지의 아이템은 다 강화된 상태임
            // 이전까지 강화된 아이템 + 영향 줄 수 있는 아이템 개수를 계속해서 비교
            answer = maxOf(answer, A[i].takeIf { it > 0 }?.let { i + it } ?: 0).coerceAtMost(N)
        }
        print(answer)
    }
}