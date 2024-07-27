import kotlin.system.exitProcess

private var N = 0
private lateinit var a: List<Set<Int>>
private lateinit var answer: IntArray
private lateinit var v: List<BooleanArray>

private fun dfs(prev: Int, now: Int) {
    if (now == N) {
        print(answer.joinToString("\n"))
        exitProcess(0)
    }

    for (n in 1..9) {
        if (prev != n && n - 1 in a[now] && v[now][n].not()) {
            answer[now] = n
            v[now][n] = true
            dfs(n, now + 1)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    N = readLine().toInt()
    a = List(N) { readLine().split(" ").drop(1).map { it.toInt() - 1 }.toSet() }
    answer = IntArray(N)
    v = List(N + 1) { BooleanArray(9 + 1) { false } }
    dfs(-1, 0)
    print(-1)
}