package baekjoon.implementation

class `3991` {
    fun solution() {
        val (h, w, c) = readln().split(" ").map(String::toInt)
        val b = readln().split(" ").map(String::toInt).toIntArray()
        var i = 0
        var j = 0
        var now = 1
        var toRight = true
        val answer = List(h) { IntArray(w) }
        while (i < h) {
            while (b[now - 1] > 0 && j in 0 until w) {
                b[now - 1]--
                answer[i][j] = now
                toRight.takeIf { it }?.let { j++ } ?: j--
            }
            if (b[now - 1] == 0) {
                now++
            }
            if (j == -1 || j == w) {
                i++
                toRight = toRight.not()
                toRight.takeIf { it }?.let { j = 0 } ?: j--
            }
        }
        print(answer.joinToString("\n") { it.joinToString("") })
    }
}