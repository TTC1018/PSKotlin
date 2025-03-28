package baekjoon.constructive

class `32944` {
    fun solution() {
        val (N, M, K) = readln().split(" ").map(String::toInt)
        if (N == M) {
            print(-1)
        } else {
            // 앞에 K 길이의 연속된 증가 수열 만들기
            // 끊고 이어서 M 길이의 LIS 만들기
            print(
                buildList {
                    addAll((1 until K))
                    add(N) // 연속된 증가 수열 마지막 숫자 (수열 최댓값으로 차단)
                    addAll((K until K + (M - K))) // 남은 숫자로 LIS 수열 만들기
                    add(N - 1) // LIS 마지막 숫자 (N - 1 배치해서 LIS 종료)
                    addAll(N - 2 downTo M) // 남은 숫자 내림차순 배치
                }.joinToString(" ")
            )
        }
    }
}