package baekjoon.math

import java.util.BitSet
import java.util.StringTokenizer

class `10166` {
    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    fun main() {
        StringTokenizer(readln()).run {
            val D1 = nextToken().toInt()
            val D2 = nextToken().toInt()
            val counter = BitSet(D2 * D2)
            for (d in D1..D2) {
                for (n in 1..d) {
                    val gcd = gcd(d, n)
                    if (counter.get(n.div(gcd).times(D2) + d.div(gcd))) continue
                    counter.set(n.div(gcd).times(D2) + d.div(gcd))
                }
            }
            print(counter.cardinality())
        }
    }
}