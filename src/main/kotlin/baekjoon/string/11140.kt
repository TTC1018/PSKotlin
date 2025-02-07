package baekjoon.string

class `11140` {
    private val T = "lol"

    fun solution() {
        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    val W = readln()
                    val regex = "l.l".toRegex()
                    // 없으면 그냥 lol 세글자 추가하면 됨
                    // 줄여서 lol 만들기 신경 쓸 필요 없음
                    // l*ol, lo*l -> 그냥 저 자리에 필요한 글자 추가하면 됨
                    // 즉 모든 상황은 3번 이하로 끝나야 됨
                    // ll -> 1개만 더
                    // l*l -> 없애고 추가 2번임
                    //
                    when {
                        T in W -> appendLine(0)
                        "lo" in W || "ll" in W || "ol" in W || regex.containsMatchIn(W) -> appendLine(1)
                        "l" in W || "o" in W -> appendLine(2)
                        else -> appendLine(3)
                    }
                }
            }.dropLast(1)
        )
    }
}