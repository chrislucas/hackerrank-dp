import kotlin.math.max

/**
 * https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
 * */


fun TLE(arr: Array<Int>): Int {
    val lim = arr.size
    var max = 0
    for ( i in 0 until lim) {
        var s = 2
        while (i + s  < lim) {
            var parcial = arr[i]
            for ( j in i+s until lim step s) {
                parcial += arr[j]
                if (parcial > max)
                    max = parcial
            }
            s++
        }
    }
    return max
}

fun maxSubsetSum(arr: Array<Int>): Int {
    return if (arr.size == 1)
        arr[0]
    else {
        arr[0] = max(0, arr[0])
        arr[1] = max(arr[0], arr[1])
        for (i in 2 until arr.size) {
            val p = arr[i] + arr[i-2]
            val q = arr[i-1]
            arr[i] = max(p, q)
        }
        arr[arr.size-1]
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    println(maxSubsetSum(arr))
}