package baekjoon.adhoc

class `32711` {
    fun solution() {
        val n = readln().toInt()
        val a = readln().split(" ").map(String::toInt)
        val s = IntArray(n + 1)
        for (i in 0 until n) {
            s[i + 1] = (s[i] + (a[i] and 1)) and 1
        }
        if (s[n] == 1) {
            print(1)
            return
        }
        for (i in 1 until n) {
            if (s[i] == 0) {
                print(1)
                return
            }
        }
        print(0)
    }
}