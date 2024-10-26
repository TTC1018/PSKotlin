package baekjoon.adhoc

import kotlin.math.absoluteValue

class `31834` {
    fun main() = with(System.`in`.bufferedReader()) {
        // 가장 '빠른' 탈출 중 최소 비용
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val (N, S, E) = readLine().split(" ").map(String::toInt)
            // 목적지 좌측/우측 모두 지나가야 됨
            // 1자면 0번
            // 처음, 끝 시작이면 1번
            // 바로 붙어있으면 좌/우로 쭉가서 다시 우/좌에서 시작
            // 나머지 2번
            if ((S == 1 && E == N) || (S == N && E == 1)) {
                sb.appendLine(0)
            } else if (S == 1 || S == N || (S - E).absoluteValue == 1) {
                sb.appendLine(1)
            } else {
                sb.appendLine(2)
            }
        }
        print(sb.dropLast(1))
    }
}