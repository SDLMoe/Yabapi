package moe.sdl.yabapi.util.encoding

import kotlinx.coroutines.withContext
import moe.sdl.yabapi.Platform
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

private const val POLY = 0xEDB88320u
private const val INIT = 0xFFFFFFFFu

private const val TABLE_LENGTH = 256

@SharedImmutable
private val table: UIntArray by lazy {
    val table = UIntArray(TABLE_LENGTH)
    var fwd: UInt // forward
    repeat(TABLE_LENGTH) { i ->
        fwd = i.toUInt()
        repeat(8) {
            fwd = if ((fwd and 1u) != 0u)
                (fwd shr 1) xor POLY
            else fwd shr 1
        }
        table[i] = fwd
    }
    table
}

public data class Crc32(
    val result: UInt,
    val lastIndex: UInt,
)

public fun crc32(data: String): Crc32 {
    var lastIndex: UInt? = null
    val result = data.foldIndexed(INIT) { ord, acc, char ->
        val index = (acc xor char.code.toUInt()) and 0xFFu
        if (ord == data.length - 1) lastIndex = index
        (acc shr 8) xor table[index.toInt()]
    }
    requireNotNull(lastIndex)
    return Crc32(result, lastIndex!!) // No smart cast, because of value passed to closure
}

private fun getCrcIndex(value: UInt): UInt =
    table.indexOfFirst { it shr 24 == value }.toUInt()

public data class CheckResult(
    val isSucceed: Boolean = false,
    val result: String? = null,
)

private fun deepCheck(data: String, index: UIntArray): CheckResult {
    var tc: UInt
    var str = ""
    var hash = crc32(data).result

    tc = (hash and 0xFFu) xor index[2]
    if (tc !in 48u..57u) return CheckResult(false)
    str += (tc - 48u).toString()
    hash = table[index[2].toInt()] xor (hash shr 8)

    tc = (hash and 0xFFu) xor index[1]
    if (tc !in 48u..57u) return CheckResult(false)
    str += (tc - 48u).toString()
    hash = table[index[1].toInt()] xor (hash shr 8)

    tc = (hash and 0xFFu) xor index[0]
    if (tc !in 48u..57u) return CheckResult(false)
    str += (tc - 48u).toString()
    // hash = table[index[0].toInt()] xor (hash shr 8)

    return CheckResult(true, str)
}

/**
 * Inverse CRC 32 only applicable for raw data LESS THAN 4 bytes,
 *
 * The goal here is to compute out the mid (32 bit int) of danmaku sender.
 *
 * average cost about 50 ms
 * @param input hex crc32
 * @return [String] raw data need to convert manually
 */
public suspend fun inverseCrc32(
    input: String,
    context: CoroutineContext = Platform.ioDispatcher
): String? = withContext(context) {
    var ht = input.toUInt(16) xor INIT
    val index = UIntArray(4)
    for (i in 3 downTo 0) {
        index[3 - i] = getCrcIndex(ht shr (i * 8))
        val snum = table[index[3 - i].toInt()]
        ht = ht xor (snum shr ((3 - i) * 8))
    }
    for (i in 0..100000000) {
        val lastIndex = crc32(i.toString()).lastIndex
        if (lastIndex == index[3]) {
            val deepCheckData = deepCheck(i.toString(), index)
            if (deepCheckData.isSucceed) return@withContext i.toString() + deepCheckData.result
        }
    }
    null
}
