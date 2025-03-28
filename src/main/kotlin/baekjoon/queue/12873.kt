package baekjoon.queue

import java.util.LinkedList

class `12873` {
    fun solution() {
        val q = LinkedList<Long>().apply {
            addAll((1..readln().toLong()))
        }
        var stage = 1L
        while (q.size > 1) {
            var cnt = (stage * stage * stage - 1).mod(q.size)
            while (cnt-- > 0) {
                q.addLast(q.removeFirst())
            }
            q.removeFirst()
            stage++
        }
        print(q.first())
    }
}