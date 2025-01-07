package baekjoon.adhoc

class `17074` {
    fun solution() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val a = readLine().split(" ").map(String::toInt)
    val target = a.sorted()

    when {
        a == target -> print(N)
        else -> {
            var cnt = 0
            var (p1, p2) = 0 to 0
            for (i in 1 until N) {
                if (a[i - 1] > a[i]) {
                    cnt++
                    p1 = i - 1; p2 = i
                }
            }

            if (cnt == 1) {
                var answer = 0
                if (a.getOrElse(p1 - 1) { Int.MIN_VALUE } <= a[p2]) answer++
                if (a[p1] <= a.getOrElse(p2 + 1) { Int.MAX_VALUE }) answer++
                print(answer)
            } else {
                print(0)
            }
        }
    }
}
}