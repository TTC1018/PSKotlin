package baekjoon.graph

class `32248` {
    private lateinit var A: List<Int>
    private lateinit var V: BooleanArray
    private var cut = 0
    private val order = mutableListOf<Int>()
    private lateinit var cycle: List<Int>

    private fun findCycle(x: Int) {
        if (V[x]) {
            cut = order.indexOfFirst { it == x }
            cycle = order.drop(cut)
            return
        }

        order += x
        V[x] = true
        findCycle(A[x])
    }

    fun solution() {
        val (N, T) = readln().split(' ').map(String::toLong)
        V = BooleanArray(N.toInt()) { false }
        A = readln().split(" ").map { it.toInt() - 1 }
        // x -> Ax로 변환하는 것을 T번 반복, T는 매우 큼
        // n <= 100 이기 때문에 사이클이 분명히 존재함
        // 사이클 찾고 T를 나누기
        findCycle(0)
        if (T < cut) {
            print(order[T.toInt()] + 1)
        } else {
            print(cycle[T.minus(cut).mod(cycle.size)] + 1)
        }
    }
}