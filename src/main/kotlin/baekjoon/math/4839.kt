package baekjoon.math

class `4839` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val P = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23)
        val prefixP = P.run {
            IntArray(size).apply {
                this[0] = 2
                for (i in 1 until this@run.size) {
                    this[i] = this[i - 1] * this@run[i]
                }
            }
        }
        val answers = ArrayDeque<String>()
        val sb = StringBuilder()
        while (true) {
            try {
                answers.clear()
                val N = readLine().toInt()
                var left = N
                if (left == 0) break

                for (i in prefixP.indices.reversed()) {
                    if (prefixP[i] <= left) {
                        answers.addFirst("${left.floorDiv(prefixP[i])}*${P.subList(0, i + 1).joinToString("*")}")
                        left %= prefixP[i]
                    }
                }
                if (left > 0) answers.addFirst("$left")
                sb.appendLine("$N = ${answers.joinToString(" + ")}")
            } catch (e: Exception) {
                break
            }
        }
        print(sb.dropLast(1))
    }
}