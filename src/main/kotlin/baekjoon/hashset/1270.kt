package baekjoon.hashset

class `1270` {
    fun solution() = with(System.`in`.bufferedReader()) {
        print(
            StringBuilder().apply {
                repeat(readLine().trim().toInt()) {
                    readLine().trim().split(" ").map(String::toLong)
                        .run {
                            val half = first().floorDiv(2)
                            drop(1)
                            groupingBy { it }
                                .eachCount()
                                .run {
                                    appendLine(
                                        filter { (_, v) -> v > half }
                                            .keys.takeIf { it.isNotEmpty() }?.first() ?: "SYJKGW"
                                    )
                                }
                        }
                }
            }.dropLast(1)
        )
    }
}