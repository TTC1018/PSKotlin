package baekjoon.bruteforce

class `2659` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val timeNum = buildList {
            (1..9).forEach { a ->
                (1..9).forEach { b ->
                    (1..9).forEach { c ->
                        (1..9).forEach { d ->
                            add(
                                "$a$b$c$d".let { it + it }.let { t ->
                                    buildList {
                                        for (i in 0..3) {
                                            add(t.slice(i..i + 3).toInt())
                                        }
                                    }.minOf { it }
                                }
                            )
                        }
                    }
                }
            }
        }.distinct()
            .sorted()
        val target = readLine().split(" ").joinToString("").let { it + it }
            .let { t ->
                buildList {
                    for (i in 0..3) {
                        add(t.slice(i until i + 4).toInt())
                    }
                }.minOf { it }
            }
        print(timeNum.indexOf(target) + 1)
    }
}