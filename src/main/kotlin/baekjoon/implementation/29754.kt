package baekjoon.implementation

class `29754` {
    private data class Memo(
        val dayBit: Int = 0,
        val time: Int,
    ) {
        operator fun plus(o: Memo) = Memo(dayBit or o.dayBit, time + o.time)
    }

    private val Int.bit get() = 1 shl this

    private val String.time get() = split(":").map(String::toInt).run { first() * 60 + last() }

    private val TARGET_TIME = 60 * 60

    fun solution() {
        val (N, M) = readln().split(" ").map(String::toInt)
        val B = List(N) {
            readln().split(" ")
        }.sortedWith(compareBy({ it[1].toInt() }, { it[0] }))
        val answer = List(M / 7) { mutableSetOf<String>() }
        val counter = mutableMapOf<String, Memo>()
        var week = 0
        for ((name, day, s, e) in B) {
            val now = Memo(day.toInt().bit, e.time - s.time)
            if (day.toInt().minus(1).div(7) == week) {
                counter[name]?.run { counter.put(name, this + now) } ?: counter.put(name, now)
            } else {
                answer[week++].addAll(
                    counter.filterValues {
                        it.dayBit.countOneBits() >= 5 && it.time >= TARGET_TIME
                    }.keys
                )
                counter.clear()
                counter[name] = now
            }
        }
        counter.takeIf { it.isNotEmpty() }?.run {
            answer[week].addAll(
                filterValues {
                    it.dayBit.countOneBits() >= 5 && it.time >= TARGET_TIME
                }.keys
            )
        }

        print(
            answer
                .flatten()
                .groupingBy { it }
                .eachCount()
                .filterValues { it == M / 7 }
                .keys
                .takeIf { it.isNotEmpty() }
                ?.sorted()
                ?.joinToString("\n")
                ?: -1
        )
    }
}