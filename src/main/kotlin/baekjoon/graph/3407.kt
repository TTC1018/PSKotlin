package baekjoon.graph

class `3407` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val W = buildSet {
            add("h"); add("he")
            addAll("Li\tBe\tB\tC\tN\tO\tF\tNe".split("\t").map(String::lowercase))
            addAll("Na\tMg\tAl\tSi\tP\tS\tCl\tAr".split("\t").map(String::lowercase))
            addAll(
                "K\tCa\tSc\tTi\tV\tCr\tMn\tFe\tCo\tNi\tCu\tZn\tGa\tGe\tAs\tSe\tBr\tKr".split("\t")
                    .map(String::lowercase)
            )
            addAll(
                "Rb\tSr\tY\tZr\tNb\tMo\tTc\tRu\tRh\tPd\tAg\tCd\tIn\tSn\tSb\tTe\tI\tXe".split("\t")
                    .map(String::lowercase)
            )
            addAll(
                "Cs\tBa\tHf\tTa\tW\tRe\tOs\tIr\tPt\tAu\tHg\tTl\tPb\tBi\tPo\tAt\tRn".split("\t").map(String::lowercase)
            )
            addAll("Fr\tRa\tRf\tDb\tSg\tBh\tHs\tMt\tDs\tRg\tCn\tFl\tLv".split("\t").map(String::lowercase))
            addAll("La\tCe\tPr\tNd\tPm\tSm\tEu\tGd\tTb\tDy\tHo\tEr\tTm\tYb\tLu".split("\t").map(String::lowercase))
            addAll("Ac\tTh\tPa\tU\tNp\tPu\tAm\tCm\tBk\tCf\tEs\tFm\tMd\tNo\tLr".split("\t").map(String::lowercase))
        }
        print(
            StringBuilder().apply {
                val q = ArrayDeque<Int>()
                var T = readLine().toInt()
                loop@ while (T-- > 0) {
                    val S = readLine()
                    val V = BooleanArray(S.length + 1).apply { this[0] = true }
                    q.clear()
                    q.add(0)
                    while (q.isNotEmpty()) {
                        val i = q.removeFirst()
                        if (i == S.length) {
                            appendLine("YES")
                            continue@loop
                        }

                        if ("${S[i]}" in W && V[i + 1].not()) {
                            V[i + 1] = true
                            q.add(i + 1)
                        }
                        if (i + 1 <= S.lastIndex && "${S[i]}${S[i + 1]}" in W && V[i + 2].not()) {
                            V[i + 2] = true
                            q.add(i + 2)
                        }
                    }
                    appendLine("NO")
                }
            }.dropLast(1)
        )
    }
}