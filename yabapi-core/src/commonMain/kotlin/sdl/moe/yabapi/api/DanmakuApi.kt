package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.VIDEO_DANMAKU_CALENDAR_URL
import sdl.moe.yabapi.consts.internal.VIDEO_DANMAKU_WEB_URL
import sdl.moe.yabapi.consts.internal.VIDEO_HISTORY_DANMAKU_GET_URL
import sdl.moe.yabapi.consts.protoBuf
import sdl.moe.yabapi.data.danmaku.DanmakuCalendarResponse
import sdl.moe.yabapi.data.danmaku.DanmakuMetadataResponse
import sdl.moe.yabapi.data.danmaku.DanmakuResponse
import sdl.moe.yabapi.enums.danmaku.DanmakuType
import sdl.moe.yabapi.enums.danmaku.DanmakuType.VIDEO
import sdl.moe.yabapi.util.Logger
import sdl.moe.yabapi.util.encoding.avInt
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("DanmakuApi") }

/**
 * 获取视频弹幕
 * @param cid 分p id
 * @param part 弹幕分块, 现时(22年1月)为 6 分钟一分块
 * @param aid 可选 av 号
 * @param type [DanmakuType] 弹幕类型, 仅已知视频
 * @return [DanmakuResponse]
 */
@ExperimentalSerializationApi
public suspend fun BiliClient.getDanmaku(
    cid: Int,
    part: Int = 1,
    aid: Int? = null,
    type: DanmakuType = VIDEO,
    context: CoroutineContext = this.context,
): DanmakuResponse = withContext(context) {
    val showAid = aid?.let { " (av$it)" } ?: ""
    logger.debug { "Getting danmaku for cid $cid$showAid part $part..." }
    val bytes: ByteArray = client.get(VIDEO_DANMAKU_WEB_URL) {
        parameter("type", type.code)
        parameter("oid", cid)
        aid?.let { parameter("pid", aid) }
        parameter("segment_index", part)
    }
    protoBuf.decodeFromByteArray<DanmakuResponse>(bytes).also {
        logger.debug { "Got danmaku for cid $cid$showAid part $part, count: ${it.danmakus.count()}" }
        logger.verbose { "Danmakus: $it" }
    }
}

@ExperimentalSerializationApi
public suspend inline fun BiliClient.getDanmaku(
    cid: Int, part: Int = 1, bid: String, type: DanmakuType = VIDEO, context: CoroutineContext = this.context,
): DanmakuResponse = getDanmaku(cid, part, bid.avInt, type, context)

// not implemented, kotlinx.serialization not support proto3 packed repeat message
@ExperimentalSerializationApi
public suspend fun BiliClient.getDanmakuMetadata(
    cid: Int,
    aid: Int? = null,
    type: DanmakuType = VIDEO,
    context: CoroutineContext = this.context,
): DanmakuMetadataResponse = withContext(context) {
    val showAid = aid?.let { " (av$it)" } ?: ""
    logger.debug { "Getting danmaku metadata for cid $cid$showAid..." }
    val bytes: ByteArray = client.get(VIDEO_DANMAKU_WEB_URL) {
        parameter("type", type.code)
        parameter("oid", cid)
        aid?.let { parameter("pid", aid) }
    }
    protoBuf.decodeFromByteArray<DanmakuMetadataResponse>(bytes).also {
        logger.debug { "Got danmaku metadata for cid $cid$showAid: $it}" }
    }
}

@ExperimentalSerializationApi
public suspend inline fun BiliClient.getDanmakuMetadata(
    cid: Int, bid: String, type: DanmakuType = VIDEO, context: CoroutineContext = this.context,
): DanmakuMetadataResponse = getDanmakuMetadata(cid, bid.avInt, type, context)

/**
 * 获取弹幕日历, 返回有弹幕的日期
 * @param cid 分 p 号
 * @param year 公元年
 * @param month 公历月
 * @param type 弹幕类型
 * @return [DanmakuCalendarResponse]
 * @see getHistoryDanmaku
 */
public suspend fun BiliClient.getDanmakuCalendar(
    cid: Int,
    year: Int,
    month: Int,
    type: DanmakuType = VIDEO,
    context: CoroutineContext = this.context,
): DanmakuCalendarResponse = withContext(context) {
    val date = "$year-${month.toString().padStart(2, '0')}"
    logger.debug { "Getting calendar for cid$cid in $date" }
    client.get<String>(VIDEO_DANMAKU_CALENDAR_URL) {
        parameter("type", type.code)
        parameter("oid", cid)
        parameter("month", date)
    }.deserializeJson<DanmakuCalendarResponse>().also {
        logger.debug { "Got calendar for cid$cid in $date: $it" }
    }
}

/**
 * 获取历史弹幕, 可通过 [getDanmakuCalendar] 得知哪些天数有弹幕
 * @param cid 分 p 号
 * @param date 日期, YYYY-MM-DD 格式, 与 [getDanmakuCalendar] 的返回一致
 * @param type 弹幕类型
 * @see getDanmakuCalendar
 */
@ExperimentalSerializationApi
public suspend fun BiliClient.getHistoryDanmaku(
    cid: Int,
    date: String,
    type: DanmakuType = VIDEO,
    context: CoroutineContext = this.context,
): DanmakuResponse =
    withContext(context) {
        logger.debug { "Getting History Danmaku for cid$cid on $date..." }
        client.get<ByteArray>(VIDEO_HISTORY_DANMAKU_GET_URL) {
            parameter("type", type.code)
            parameter("oid", cid)
            parameter("date", date)
        }.let<ByteArray, DanmakuResponse> {
            protoBuf.decodeFromByteArray(it)
        }.also {
            logger.debug { "Got History Danmaku for cid$cid on $date: danmaku count ${it.danmakus.count()}" }
            logger.verbose { "$it" }
        }
    }

/**
 * 对 kotlinx.datetime 的封装支持
 * @see getHistoryDanmaku
 */
@ExperimentalSerializationApi
public suspend fun BiliClient.getHistoryDanmaku(
    cid: Int,
    date: LocalDate,
    type: DanmakuType = VIDEO,
    context: CoroutineContext = this.context,
): DanmakuResponse = run {
    val year = date.year.toString()
    val month = date.monthNumber.toString().padStart(2, '0')
    val day = date.dayOfMonth.toString().padStart(2, '0')
    val dateFormatted = "$year-$month-$day"
    getHistoryDanmaku(cid, dateFormatted, type, context)
}
