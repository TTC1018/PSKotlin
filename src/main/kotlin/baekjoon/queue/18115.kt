package baekjoon.queue

class `18115` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        val A = readLine().split(" ").map(String::toInt).reversed()
        val q = ArrayDeque<Int>()
        for (i in A.indices) {
            when (A[i]) {
                1 -> q.addFirst(i + 1)
                2 -> {
                    q.removeFirst().run {
                        q.addFirst(i + 1)
                        q.addFirst(this)
                    }
                }
                3 -> {
                    q.addLast(i + 1)
                }
            }
        }
        print(q.joinToString(" "))
    }
}