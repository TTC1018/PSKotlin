package baekjoon.differentialcrpytanalysis

class `30506` {
    fun solution() {
        // 이긴 수만 알 수 있음
        // 첫 게임에서 가위만 냄
        val RSP = "2".repeat(100).toCharArray()
        var K = readln().toInt()
        val answer = StringBuilder()
        repeat(100) {
            RSP[it] = '0'
            println("? ${RSP.joinToString("")}")
            System.out.flush()
            val result = readln().toInt()
            // 값이 증가 -> 바위를 내면 이기는 것
            // 값이 감소 -> 가위를 내면 이기는 것
            // 값 유지 -> 보를 내면 이기는 것
            when (result) {
                K + 1 -> {
                    answer.append('2')
                    K++
                }

                K -> {
                    RSP[it] = '5'
                    answer.append('0')
                    K++
                }

                K - 1 -> {
                    RSP[it] = '2'
                    answer.append('5')
                }
            }
        }
        println("! $answer")
        System.out.flush()
    }
}