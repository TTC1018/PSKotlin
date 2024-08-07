package baekjoon.adhoc

class `30021` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        if (N == 2) {
            print("NO")
        } else {
            print("YES\n1")
            if (N >= 3) {
                print(" 3 2${(4..N).joinToString(" ").takeIf { it.isNotBlank() }?.run { " $this" } ?: ""}")
            }
        }
    }
}