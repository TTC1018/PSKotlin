package baekjoon.implementation

class `27969` {
    fun solution() {
        val S = readln()
        var answer = 0
        var i = 0
        while (i <= S.lastIndex) {
            val s = S[i]
            when {
                s.isDigit() -> {
                    while (S[i].isDigit() && i++ <= S.lastIndex) { }
                    answer += 8
                }
                s.isLetter() -> {
                    while (S[i].isLetter() && i++ <= S.lastIndex) {
                        answer++
                    }
                    answer += 12
                }
                s == '[' -> {
                    answer += 8
                    i++
                }
                else -> i++
            }
        }
        print(answer)
    }
}