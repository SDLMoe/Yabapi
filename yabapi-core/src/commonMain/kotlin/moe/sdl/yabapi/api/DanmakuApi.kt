package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.VIDEO_DANMAKU_CALENDAR_URL
import moe.sdl.yabapi.consts.internal.VIDEO_DANMAKU_WEB_URL
import moe.sdl.yabapi.consts.internal.VIDEO_HISTORY_DANMAKU_GET_URL
import moe.sdl.yabapi.data.danmaku.DanmakuCalendarResponse
import moe.sdl.yabapi.data.danmaku.DanmakuResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.deserializeProto
import moe.sdl.yabapi.enums.danmaku.DanmakuType
import moe.sdl.yabapi.enums.danmaku.DanmakuType.VIDEO
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.encoding.avInt
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
    client.get(VIDEO_DANMAKU_WEB_URL) {
        parameter("type", type.code)
        parameter("oid", cid)
        aid?.let { parameter("pid", aid) }
        parameter("segment_index", part)
    }.body<ByteArray>().deserializeProto<DanmakuResponse>().also {
        logger.debug { "Got danmaku for cid $cid$showAid part $part, count: ${it.danmakus.count()}" }
        logger.verbose { "Danmakus: $it" }
    }
}

@ExperimentalSerializationApi
public suspend inline fun BiliClient.getDanmaku(
    cid: Int,
    part: Int = 1,
    bid: String,
    type: DanmakuType = VIDEO,
    context: CoroutineContext = this.context,
): DanmakuResponse = getDanmaku(cid, part, bid.avInt, type, context)

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
    client.get(VIDEO_DANMAKU_CALENDAR_URL) {
        parameter("type", type.code)
        parameter("oid", cid)
        parameter("month", date)
    }.body<String>().deserializeJson<DanmakuCalendarResponse>().also {
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
): DanmakuResponse = withContext(context) {
    logger.debug { "Getting History Danmaku for cid$cid on $date..." }
    client.get(VIDEO_HISTORY_DANMAKU_GET_URL) {
        parameter("type", type.code)
        parameter("oid", cid)
        parameter("date", date)
    }.body<ByteArray>().deserializeProto<DanmakuResponse>().also {
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
