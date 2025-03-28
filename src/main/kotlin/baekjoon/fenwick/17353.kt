package baekjoon.fenwick

class `17353` {
    private var N = 0
    private var Q = 0
    private lateinit var A: List<Int>

    // [L, R]에 연산을 하면 (i - L) + 1이 각 자리에 더해짐
    // 그러면 (i + 1)은 몇번 이뤄졌는지 횟수를 기록해두고,
    // -L은 합으로 계산해두면 최종 계산 값을 쉽게 연산 가능
    private lateinit var FT: IntArray // (i + 1) 횟수 기록
    private lateinit var FT2: IntArray // -L 합 기록

    private fun update(s: Int, e: Int) {
        rangeUpdate(s, e, 1, FT)
        rangeUpdate(s, e, -s, FT2)
    }

    /**
     * [[s], [e]] 구간을 변화시키는 것은
     * s에 [newValue]를 추가하고, (e + 1)부터 [newValue]를 차감하는 것
     */
    private fun rangeUpdate(s: Int, e: Int, newValue: Int, arr: IntArray) {
        updateFenwick(s, newValue, arr)
        updateFenwick(e + 1, -newValue, arr)
    }

    /**
     * - 어떤 수의 음수는 2의 보수
     * - 2의 보수란, 비트를 반전시키고 1을 더한 값
     * - 이 2의 보수와 AND 연산을 하면 가장 우측 1비트(LSB)만 남음
     * - LSB를 더하면 자신을 포함하는 구간이 차례로 등장함
     * - 펜윅트리를 업데이트 한다는 것은, 이 구간들에 새로운 값을 더하거나 빼주는 것
     */
    private fun updateFenwick(now: Int, newValue: Int, arr: IntArray) {
        var x = now
        while (x <= N) {
            arr[x] += newValue
            x += (x and -x)
        }
    }

    /**
     * - LSB를 빼면 하위 구간을 탐색
     * - 계속해서 LSB를 빼면서 다음 구간의 값을 누적
     */
    private fun search(now: Int, arr: IntArray): Long {
        var i = now
        var result = 0L
        while (i > 0) {
            result += arr[i]
            i -= (i and -i)
        }
        return result
    }


    fun solution() {
        N = readln().toInt()
        A = readln().split(" ").map(String::toInt)
        Q = readln().toInt()
        FT = IntArray(N + 1)
        FT2 = IntArray(N + 1)
        val sb = StringBuilder()
        repeat(Q) {
            val q = readln().split(" ").map(String::toInt)
            when (q[0]) {
                1 -> update(q[1], q[2])
                2 -> sb.appendLine(A[q[1] - 1] + (q[1] + 1) * search(q[1], FT) + search(q[1], FT2))
            }
        }
        print(sb.dropLast(1))
    }
}