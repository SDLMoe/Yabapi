// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

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
import sdl.moe.yabapi.util.avInt
import sdl.moe.yabapi.util.logger

public object DanmakuApi : BiliApi {
    override val apiName: String = "danmaku"

    init {
        BiliClient.registerApi(this)
    }

    @ExperimentalSerializationApi
    public suspend fun BiliClient.getDanmaku(
        cid: Int,
        part: Int = 1,
        aid: Int? = null,
        type: DanmakuType = VIDEO,
    ): DanmakuResponse = withContext(dispatcher) {
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
        cid: Int, part: Int = 1, bid: String, type: DanmakuType = VIDEO,
    ): DanmakuResponse = getDanmaku(cid, part, bid.avInt, type)

    // not implemented, kotlinx.serialization not support proto3 packed repeat message
    @ExperimentalSerializationApi
    public suspend fun BiliClient.getDanmakuMetadata(
        cid: Int,
        aid: Int? = null,
        type: DanmakuType = VIDEO,
    ): DanmakuMetadataResponse = withContext(dispatcher) {
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
        cid: Int, bid: String, type: DanmakuType = VIDEO,
    ): DanmakuMetadataResponse = getDanmakuMetadata(cid, bid.avInt, type)

    public suspend fun BiliClient.getDanmakuCalendar(
        cid: Int,
        year: Int,
        month: Int,
        type: DanmakuType = VIDEO,
    ): DanmakuCalendarResponse =
        withContext(dispatcher) {
            val date = "$year-${month.toString().padStart(2, '0')}"
            logger.debug { "Getting calendar for cid$cid in $date" }
            client.get<DanmakuCalendarResponse>(VIDEO_DANMAKU_CALENDAR_URL) {
                parameter("type", type.code)
                parameter("oid", cid)
                parameter("month", date)
            }.also {
                logger.debug { "Got calendar for cid$cid in $date: $it" }
            }
        }

    @ExperimentalSerializationApi
    public suspend fun BiliClient.getHistoryDanmaku(
        cid: Int,
        date: String,
        type: DanmakuType = VIDEO,
    ): DanmakuResponse =
        withContext(dispatcher) {
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
     * @param date YYYY-MM-DD
     */
    @ExperimentalSerializationApi
    public suspend fun BiliClient.getHistoryDanmaku(
        cid: Int, date: LocalDate, type: DanmakuType = VIDEO
    ): DanmakuResponse = run {
        val year = date.year.toString()
        val month = date.monthNumber.toString().padStart(2, '0')
        val day = date.dayOfMonth.toString().padStart(2, '0')
        val dateFormatted = "$year-$month-$day"
        getHistoryDanmaku(cid, dateFormatted, type)
    }
}
