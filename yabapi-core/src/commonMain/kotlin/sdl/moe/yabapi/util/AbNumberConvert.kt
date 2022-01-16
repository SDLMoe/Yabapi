@file:Suppress("MagicNumber")

package sdl.moe.yabapi.util

private const val TABLE = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF"
private const val XOR_VALUE = 177451812L
private const val ADD_VALUE = 8728348608L

private val ss = intArrayOf(11, 10, 3, 8, 4, 6, 2, 9, 5, 7)

private val mp: HashMap<String, Int> by lazy {
    val map = HashMap<String, Int>()
    for (i in 0..57) {
        map[TABLE.substring(i, i + 1)] = i
    }
    map
}
private val mp2: HashMap<Int, String> by lazy {
    val map = HashMap<Int, String>()
    for (i in 0..57) {
        map[i] = TABLE.substring(i, i + 1)
    }
    map
}

@Suppress("SameParameterValue")
private fun power(a: Int, b: Int): Long {
    var power: Long = 1
    repeat(b) { power *= a.toLong() }
    return power
}

/**
 * BV 转 AV 号，转换结果为 Long, 形如:(av) 1234567
 * @receiver String 输入形如 BVra123abc 的 String, 大小写敏感
 */
public val String.av: Long
    get() {
        var r: Long = 0
        for (i in 0..57) {
            val s1 = TABLE.substring(i, i + 1)
            mp[s1] = i
        }
        for (i in 0..5) {
            r += mp[this.substring(ss[i], ss[i] + 1)]!! * power(58, i)
        }
        return r - ADD_VALUE xor XOR_VALUE
    }

public val String.avInt: Int
    get() = av.toInt()

public val String.avString: String
    get() = "av$av"

/**
 * AV 转 BV 号，转换结果为字符串，形如：BVra123abc
 * @receiver String 输入形如 av1234567 的 String, 大小写不敏感
 */
public val Long.bv: String
    get() {
        var s = this
        var str = "BV1  4 1 7  "
        s = (s xor XOR_VALUE) + ADD_VALUE
        for (i in 0..5) {
            val r: String? = mp2[(s / power(58, i) % 58).toInt()]
            if (r != null) {
                str = str.replaceRange(ss[i]..ss[i], r)
            }
        }
        return str
    }

public val Int.bv: String
    get() = this.toLong().bv

public val String.bv: String
    get() = this.lowercase().split("av")[1].toLong().bv
