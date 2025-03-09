package baekjoon.fenwick

class `2820` {
    private var N = 0
    private var M = 0
    private lateinit var X: IntArray
    private val G = mutableMapOf<Int, MutableList<Int>>()
    private lateinit var E: List<IntArray>
    private lateinit var FT: IntArray
    private lateinit var except: IntArray

    private var mark = 0
    private fun buildEuler(x: Int = 1) {
        E[x][0] = ++mark
        G[x]?.forEach { buildEuler(it) }
        E[x][1] = mark
    }

    /**
     * - [FT]에 `X[i] - X[i - 1]`의 펜윅을 저장했기 때문에
     * 구간 업데이트를 하면 값이 변하는 지점은 `X[s]`와 `X[e + 1]` 뿐임
     * - `X[s]` = `X[i] + newValue - X[i - 1]`, `X[e + 1]` = `X[e + 1] - (X[e] + newValue)`
    **/
    private fun update(x: Int, newValue: Int) {
        except[x] -= newValue
        val (s, e) = E[x]

        updateFenwick(s, newValue)
        updateFenwick(e + 1, -newValue)
    }

    private fun updateFenwick(x: Int, newValue: Int) {
        var i = x
        while (i <= N) {
            FT[i] += newValue
            i += i and -i
        }
    }

    /**
     * - [FT]에 `X[i] - X[i - 1]`의 펜윅을 저장했기 때문에
     * 누적합을 얻으면 실제 값을 얻을 수 있음
     * - `F[1] + F[2] + ... + F[i]` = `-0 + A[1] - A[1] + A[2] + ... + A[i]` = `A[i]`
    **/
    private fun search(x: Int): Int {
        var i = E[x][0]
        var result = 0
        while (i > 0) {
            result += FT[i]
            i -= i and -i
        }
        return result
    }

    fun solution() {
        readln().split(' ').map(String::toInt).run {
            N = first(); M = last()
        }
        E = List(N + 1) { IntArray(2) }
        FT = IntArray(N + 1)
        except = IntArray(N + 1)
        X = IntArray(N + 1).apply {
            this[1] = readln().toInt()
        }
        repeat(N - 1) {
            val (x, p) = readln().split(' ').map(String::toInt)
            X[it + 2] = x
            G.getOrPut(p) { mutableListOf() } += it + 2
        }
        buildEuler()

        print(
            StringBuilder().apply {
                repeat(M) {
                    val ops = readln().split(" ")
                    when (ops[0]) {
                        "p" -> {
                            val (a, x) = ops.drop(1).map(String::toInt)
                            update(a, x)
                        }

                        "u" -> {
                            val a = ops[1].toInt()
                            appendLine(X[a] + search(a) + except[a])
                        }
                    }
                }
            }.dropLast(1)
        )
    }
}