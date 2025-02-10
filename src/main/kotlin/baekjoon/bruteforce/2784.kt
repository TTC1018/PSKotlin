package baekjoon.bruteforce

import kotlin.system.exitProcess

class `2784` {
    private lateinit var S: List<String>
    private lateinit var pool: MutableMap<String, Int>
    private val pocket = mutableListOf<String>()
    private val V: BooleanArray = BooleanArray(6) { false }
    private val cands = mutableListOf<String>()

    private fun check(): Boolean {
        for (j in 0..2) {
            "${cands[0][j]}${cands[1][j]}${cands[2][j]}".let {
                if (it !in pool || pool[it]!! == 0) {
                    pocket.forEach { pool[it] = pool[it]!! + 1 }
                    pocket.clear()
                    return false
                }
                pool[it] = pool[it]!! - 1
                pocket.add(it)
            }
        }
        pocket.forEach { pool[it] = pool[it]!! + 1 }
        pocket.clear()
        return true
    }

    private fun bruteforce() {
        if (cands.size == 3) {
            if (check()) {
                print(cands.joinToString("\n"))
                exitProcess(0)
            }
            return
        }

        for (i in S.indices) {
            if (V[i].not()) {
                V[i] = true
                cands.add(S[i])
                pool[S[i]] = pool[S[i]]!! - 1
                bruteforce()
                pool[S[i]] = pool[S[i]]!! + 1
                cands.removeLast()
                V[i] = false
            }
        }
    }

    fun solution() {
        S = List(6) { readln() }.also { pool = it.groupingBy { it }.eachCount().toMutableMap() }
        bruteforce()
        print(0)
    }
}