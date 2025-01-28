package baekjoon.twopointer

import java.util.StringTokenizer

class `14572` {
    fun solution() {
        var st = StringTokenizer(readln())
        val N = st.nextToken().toInt()
        val K = st.nextToken().toInt()
        val D = st.nextToken().toInt()
        // 조건 -> 그룹 내 실력 차이가 D 이하
        // 효율성 -> (알고리즘 총 개수 - 겹치는 알고리즘 개수) * 그룹원
        // -> 그룹원은 많을 수록 좋음
        val S = List(N) {
            st = StringTokenizer(readln())
            val M = st.nextToken().toInt()
            val d = st.nextToken().toInt()
            st = StringTokenizer(readln())
            var bit = 0
            while (st.hasMoreTokens()) {
                bit = bit or st.nextToken().toInt().let { 1 shl it }
            }
            Triple(M, d, bit)
        }.sortedBy { it.second }
        val kCounter = IntArray(K + 1)
        var (l, r) = 0 to 0
        var answer = 0
        while (r < N) {
            if (S[r].second - S[l].second <= D) {
                var left = S[r].third
                var cnt = 0
                while (left > 0) {
                    if (left and 1 == 1) {
                        kCounter[cnt]++
                    }
                    left = left shr 1
                    cnt++
                }

                val total = (r - l + 1)
                answer = maxOf(answer, (kCounter.count { it >= 1 } - kCounter.count { it == total }) * total)
                r++
            } else {
                var left = S[l].third
                var cnt = 0
                while (left > 0) {
                    if (left and 1 == 1) {
                        kCounter[cnt]--
                    }
                    left = left shr 1
                    cnt++
                }
                l++
            }
        }
        print(answer)
    }
}