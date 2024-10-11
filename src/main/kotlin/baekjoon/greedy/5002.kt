package baekjoon.greedy

import kotlin.math.absoluteValue

class `5002` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val X = readLine().toInt()
        val S = readLine()
        var pp = ' '
        var w = 0
        var m = 0
        for (wm in S) {
            when (wm) {
                'M' -> m++
                'W' -> w++
            }

            if ((w - m).absoluteValue > X) {
                if (pp == ' ') {
                    pp = wm
                    when (wm) {
                        'M' -> m--
                        'W' -> w--
                    }
                } else {
                    val pm = m
                    val pw = w
                    m += if (pp == 'M') 1 else 0
                    w += if (pp == 'W') 1 else 0
                    if ((w - m).absoluteValue > X) {
                        print(pm + pw - 1)
                        return
                    }
                    pp = ' '
                }
            }
        }

        if (pp != ' ' && (w - m).absoluteValue < X) {
            print(w + m + 1)
        } else {
            print(w + m)
        }
    }
}