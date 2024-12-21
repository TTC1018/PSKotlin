package baekjoon.bruteforce

class `11566` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val r = readLine().split(" ").map(String::toInt)
        val m = readLine().toInt()
        val d = readLine().split(" ").map(String::toInt)
        // 순서대로 들리는지 확인
        // 1이 나왔을 때 -> 2 확인 -> 3 확인...
        // m <= 1000
        // 각 숫자의 인덱스를 저장
        val targetNums = r.toSet()
        val indexMap: Map<Int, MutableSet<Int>> = buildMap {
            for (i in d.indices) {
                if (d[i] in targetNums) {
                    getOrPut(d[i]) { mutableSetOf() }.add(i)
                }
            }
        }
        if (r.any { it !in indexMap }) {
            print(-1)
            return
        }

        var (a1, a2) = Int.MAX_VALUE to Int.MIN_VALUE
        (1..m).forEach { dist ->
            indexMap[r.first()]!!.forEach { real ->
                if ((0 until n).all { i -> real + dist * i in indexMap[r[i]]!! }) {
                    a1 = minOf(a1, dist - 1)
                    a2 = maxOf(a2, dist - 1)
                }
            }
        }

        if (a1 == Int.MAX_VALUE || a2 == Int.MIN_VALUE) {
            print(-1)
        } else {
            print("$a1 $a2")
        }
    }
}