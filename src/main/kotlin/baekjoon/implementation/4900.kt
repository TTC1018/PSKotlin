package baekjoon.implementation

class `4900` {
    fun solution() {
        val bitMap = buildMap {
            put("%03d".format("0111111".toInt(2)), '0')
            put("010", '1')
            put("%03d".format("1011101".toInt(2)), '2')
            put("079", '3')
            put("106", '4')
            put("%03d".format("1100111".toInt(2)), '5')
            put("%03d".format("1110111".toInt(2)), '6')
            put("%03d".format("0001011".toInt(2)), '7')
            put("%03d".format("1111111".toInt(2)), '8')
            put("%03d".format("1101011".toInt(2)), '9')
        }
        val numMap = buildMap {
            bitMap.forEach { (k, v) ->
                put(v, k)
            }
        }
        print(
            StringBuilder().apply {
                while (true) {
                    val S = readln()
                    if (S == "BYE") break

                    S.split("+").run {
                        val (A, B) = first() to last().dropLast(1)
                        val aNum = A.chunked(3).joinToString("") { bitMap[it]!!.toString() }.toInt()
                        val bNum = B.chunked(3).joinToString("") { bitMap[it]!!.toString() }.toInt()
                        val C = (aNum + bNum).toString().map { numMap[it]!! }.joinToString("")
                        appendLine("$A+$B=$C")
                    }
                }
            }.dropLast(1)
        )
    }
}