package baekjoon.greedy

class `32865` {
    private data class Prob(
        val i: Int,
        var v: Long,
    )

    fun solution() {
        val N = readln().toInt()
        val s = List(N) { readln().toLong() }.mapIndexed { i, v -> Prob(i, v) }
        // 맞고 틀리고 계속 반복해야 됨
        if (s.sumOf { it.v } + 1 != N.toLong()) {
            print(-1)
        } else {
            val answer = mutableListOf<Int>()
            val q = ArrayDeque<Prob>().apply {
                addAll(s)
                sortBy { it.v }
            }
            var switch = false
            repeat(2 * N - 1) {
                when (switch) {
                    false -> answer += q.removeFirst().i
                    true -> {
                        q.removeLast().let {
                            answer += it.i
                            if (it.v == 1L) {
                                q.addFirst(it.copy(v = 0))
                            } else {
                                q.addLast(it.copy(v = it.v - 1))
                            }
                        }
                    }
                }
                switch = switch.not()
            }
            print(answer.joinToString("\n") { "${it + 1}" })
        }
    }
}