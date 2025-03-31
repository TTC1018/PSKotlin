package baekjoon.implementation

class `25327` {
    private enum class Subject {
        kor, eng, math, `-`
    }

    private enum class Fruit {
        apple, pear, orange, `-`
    }

    private enum class Color {
        red, blue, green, `-`
    }

    fun solution() {
        val (n, m) = readln().split(" ").map(String::toInt)
        val S = List(n) {
            val (s, f, c) = readln().split(" ").mapIndexed { i, v ->
                when (i) {
                    0 -> Subject.valueOf(v)
                    1 -> Fruit.valueOf(v)
                    else -> Color.valueOf(v)
                }.ordinal
            }
            Triple(s, f, c)
        }
        val counter = List(4) { List(4) { IntArray(4) } }
        S.forEach { (s, f, c) ->
            counter[s][f][c]++
            counter[3][f][c]++
            counter[s][3][c]++
            counter[s][f][3]++
            counter[3][3][c]++
            counter[3][f][3]++
            counter[s][3][3]++
            counter[3][3][3]++
        }
        print(
            StringBuilder().apply {
                repeat(m) {
                    val (s, f, c) = readln().split(" ").mapIndexed { i, v ->
                        when (i) {
                            0 -> runCatching { Subject.valueOf(v) }.onFailure { Result.success(3) }
                            1 -> runCatching { Fruit.valueOf(v) }.onFailure { Result.success(3) }
                            else -> runCatching { Color.valueOf(v) }.onFailure { Result.success(3) }
                        }.getOrThrow().ordinal
                    }
                    appendLine(counter[s][f][c])
                }
            }.dropLast(1)
        )
    }
}