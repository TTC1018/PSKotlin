package baekjoon.graph

class `25516` {
    private var n = 0
    private var k = 0
    private lateinit var tree: List<MutableList<Int>>
    private lateinit var apple: List<Int>
    private var answer = 0

    private fun search(now: Int, depth: Int) {
        if (depth > k) {
            return
        }
        answer += apple[now]
        tree[now].forEach {
            search(it, depth + 1)
        }
    }

    fun solution() {
        readln().split(" ").map(String::toInt).run { n = first(); k = last() }
        tree = List(n) { mutableListOf<Int>() }.apply {
            repeat(size - 1) {
                val (p, c) = readln().split(" ").map(String::toInt)
                this[p] += c
            }
        }
        apple = readln().split(' ').map(String::toInt)
        search(0, 0)
        print(answer)
    }
}