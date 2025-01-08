package baekjoon.math

class `31288` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val (N, P) = readLine().split(" ")
            if (N.toInt() == 1) {
                sb.appendLine("4 2")
            } else {
                val p = P.toCharArray()
                // 소수면 3의 배수가 될 수 없음
                // 3의 배수로 만들려면 얼마가 필요한지 확인
                // 각 수에 더해야 되는 수만큼을 더해서 3의 배수로 만듦
                val left = 3 - p.map(Char::digitToInt).sumOf { it } % 3
                repeat(N.toInt()) { i ->
                    p[i].let { origin ->
                        p[i] = origin.plus(if (origin <= '7') left else left - 3)
                        sb.appendLine("${p.joinToString("")} 3")
                        p[i] = origin
                    }
                }
            }
        }
        print(sb.dropLast(1))
    }
}