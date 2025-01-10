package baekjoon.sorting

class `7387` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val B = List(6) { readLine().split(" ").map(String::toInt).sorted() }
            .sortedWith(compareBy({ it[0] }, { it[1] }))
        val (w, h) = B.map { it[0] } to B.map { it[1] }
        val possible = B.chunked(2).all { it[0] == it[1] } // AB AB AC AC BC BC
                && (w[0] == w[2])
                && (h[0] == w[4])
                && (h[2] == h[4])
        print("POSSIBLE".takeIf { possible } ?: "IMPOSSIBLE")
    }
}