package baekjoon.string

class `16163` {
    fun main() {
        val S = StringBuilder().apply {
            append('#')
            append(readln().chunked(1).joinToString("#"))
            append('#')
        }
        val n = S.length
        val p = IntArray(n) // i번째 문자를 중심으로 하는 팰린드롬의 반지름
        var r = -1 // 지금까지 찾은 팰린드롬 중 가장 오른쪽 경계
        var c = -1 // 그 팰린드롬의 중심

        for (i in 0 until n) {
            // i가 다른 팰린드롬의 경계 내에 있다면
            // 대칭 위치의 값을 활용하여 초기 반지름을 설정
            if (i <= r) {
                val oppo = 2 * c - i // 대칭점
                p[i] = minOf(p[oppo], r - i)
            }
            // i에서 양옆으로 확장하며 팰린드롬의 실제 반지름 찾기
            while (i - 1 - p[i] >= 0 && i + 1 + p[i] < n &&
                S[i - 1 - p[i]] == S[i + 1 + p[i]]
            ) {
                p[i]++
            }

            // 현재 찾은 팰린드롬이 기존의 오른쪽 경계 r을 넘어선다면, c r 갱신
            if (i + p[i] > r) {
                r = i + p[i]
                c = i
            }
        }

        // p[i]는 변형된 문자열에서의 반지름, 실제는 {(p[i] + 1) / 2} 개의 팰린드롬을 의미
        print(p.fold(0L) { acc, i -> acc + i.plus(1).div(2) })
    }
}