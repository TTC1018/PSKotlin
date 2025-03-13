package baekjoon.greedy

class `32630` {
    fun solution() {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toLong)
        // 가장 큰 숫자끼리 곱하기 (절댓값 기준)
        val maxNums = mutableListOf<Int>()
        val minNums = mutableListOf<Int>()
        A.forEachIndexed { i, v ->
            maxNums.getOrNull(1)?.let { second ->
                if (v > A[second]) {
                    maxNums.removeLast()
                    if (v > A[maxNums.first()]) {
                        maxNums.add(0, i)
                    } else {
                        maxNums += i
                    }
                }
            } ?: run { maxNums += i }
            minNums.getOrNull(1)?.let { second ->
                if (v < A[second]) {
                    minNums.removeLast()
                    if (v < A[minNums.first()]) {
                        minNums.add(0, i)
                    } else {
                        minNums += i
                    }
                }
            } ?: run {
                minNums.firstOrNull()?.let { first ->
                    if (v < A[minNums.first()]) {
                        minNums.add(0, i)
                    } else {
                        minNums += i
                    }
                } ?: run { minNums += i }
            }
        }
        val sum1 = maxNums.map { A[it] }
        val sum2 = minNums.map { A[it] }
        print(
            A.sumOf { it }.run {
                maxOf(
                    this,
                    this - sum1.sumOf { it } + sum1.let { it[0] * it[1] * 2 },
                    this - sum2.sumOf { it } + sum2.let { it[0] * it[1] * 2 }
                )
            }
        )
    }
}