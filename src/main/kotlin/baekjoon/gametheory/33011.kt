package baekjoon.gametheory

class `33011` {
    fun solution() {
        // 홀수 = 짝수인 경우
        // 채완이 필패
        // 홀수 > 짝수 or 홀수 < 짝수인 경우
        // 홀수개면 채완이 승
        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    val N = readln().toInt()
                    val A = readln().split(" ").map(String::toInt)
                    val (odd, even) = A.partition { it and 1 == 1 }
                    when {
                        odd.size == even.size -> appendLine("heeda0528")
                        else -> {
                            val bigger = maxOf(odd.size, even.size)
                            if (bigger and 1 == 1) {
                                appendLine("amsminn")
                            } else {
                                appendLine("heeda0528")
                            }
                        }
                    }
                }
            }.dropLast(1)
        )
    }
}