package baekjoon.string

class `2714` {
    private data class Direc(
        val x: Int,
        val y: Int,
    )

    private val d = listOf(
        Direc(0, 1), Direc(1, 0), Direc(0, -1), Direc(-1, 0)
    )

    private val bitMap = buildMap {
        put("00000", " ")
        ('A'..'Z').forEachIndexed { i, c ->
            put((i + 1).toString(2).padStart(5, '0'), c)
        }
    }

    fun solution() {
        print(
            StringBuilder().apply {
                repeat(readln().toInt()) {
                    readln().split(" ").let {
                        val R = it[0].toInt()
                        val C = it[1].toInt()
                        val M = it[2]

                        // 행렬로 변환
                        val B = M.chunked(C)
                        // 탐색
                        val sb = StringBuilder()
                        if (C == 1) {
                            appendLine(
                                M.chunked(5)
                                    .dropLastWhile { it.length < 5 }
                                    .map { bitMap[it]!! }
                                    .joinToString("")
                                    .trim()
                            )
                        } else {
                            var (i, j) = 0 to 0
                            var left = M.length
                            var stepR = R - 1
                            var stepC = C - 1
                            var stepCnt = stepC
                            var turnCnt = 3
                            var turnIdx = 0
                            while (left-- > 0) {
                                sb.append(B[i][j])
                                i += d[turnIdx].x
                                j += d[turnIdx].y

                                stepCnt--
                                if (stepCnt == 0) {
                                    turnIdx = (turnIdx + 1).mod(d.size)
                                    turnCnt--
                                    if (turnCnt == 0) {
                                        stepR--
                                        stepC--
                                        turnCnt = 2
                                    }
                                    if ((turnIdx - 1) and 1 == 0) {
                                        stepCnt = stepR
                                    } else {
                                        stepCnt = stepC
                                    }
                                }
                            }
                            val origin = sb.chunked(5).dropLastWhile { it.length < 5 }
                            appendLine(origin.map { bitMap[it]!! }.joinToString("").trim())
                        }
                    }
                }
            }.dropLast(1)
        )
    }
}