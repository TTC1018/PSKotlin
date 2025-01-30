package baekjoon.string

class `16300` {
    private val Char.asc get() = this - 'A'

    private val String.countArray
        get() = IntArray('Z' - 'A' + 1).let { result ->
            var i = 0
            while (i < this.length) {
                var now = this[i++]
                var cnt = ""
                while (i < this.length && this[i].isDigit()) {
                    cnt += this[i]
                    i++
                }
                result[now.asc] += cnt.toIntOrNull() ?: 1
            }
            result
        }

    fun solution() {
        val (m, k) = readln().split(" ").run { first() to last().toInt() }
        val T = readln()
        val mCounter = m.countArray.apply {
            for (i in indices) {
                this[i] *= k
            }
        }
        val tCounter = T.countArray
        print(
            mCounter.zip(tCounter).minOf { (m, t) ->
                if (t > 0) {
                    m.div(t)
                } else {
                    Int.MAX_VALUE
                }
            }
        )
    }
}