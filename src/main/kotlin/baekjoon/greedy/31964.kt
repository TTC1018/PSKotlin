package baekjoon.greedy

class `31964` {
    fun solution() {
        val N = readln().toInt()
        val X = readln().split(" ").map(String::toInt)
        val T = readln().split(" ").map(String::toInt)
        // 정방향
        val V = BooleanArray(N) { false }
        var answer = 0
        var now = 0
        var time = 0
        X.forEachIndexed { i, v ->
            answer += v - now
            now = v
            time = v
            if (now > T[i]) {
                V[i] = true
            }
        }
        // 역방향
        for (i in X.indices.reversed()) {
            answer += now - X[i]
            time += now - X[i]
            now = X[i]
            if (V[i]) continue
            if (time < T[i]) {
                answer += T[i] - time
                time = T[i]
            }
        }
        print(answer + now)
    }
}