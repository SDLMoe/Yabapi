// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.time.GET_SERVER_UTC_URL
import sdl.moe.yabapi.consts.time.GET_TIMESTAMP_URL
import sdl.moe.yabapi.data.time.TimestampGetResponse
import java.time.ZoneId
import java.time.ZonedDateTime

private val logger = KotlinLogging.logger {}

private val jsUTCRegex by lazy { Regex("""UTC\((\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+),\s*(\d+)\)""") }

public object TimeApi : BiliApi {
    init {
        BiliClient.registerApi(apiName, this)
    }

    override val apiName: String
        get() = "time"

    @Suppress("unused")
    public val BiliClient.passport: TimeApi
        get() = this@TimeApi

    /**
     * 從 API 服務器獲得當前時間戳
     */
    @JvmName("getTimestampExt")
    public suspend fun BiliClient.getTimestamp(): TimestampGetResponse = withContext(Dispatchers.IO) {
        logger.debug { "Getting timestamp" }
        client.get<TimestampGetResponse>(GET_TIMESTAMP_URL).also {
            logger.debug { "Timestamp Get Response: $it" }
        }
    }

    @JvmName("getTimestamp")
    public suspend inline fun getTimestamp(client: BiliClient): TimestampGetResponse = client.getTimestamp()

    /**
     * 獲取服務端側 UTC 時間
     * @return [ZonedDateTime]
     */
    @Suppress("MagicNumber")
    @JvmName("getServerUTCExt")
    public suspend fun BiliClient.getServerUTC(): ZonedDateTime = withContext(Dispatchers.IO) {
        logger.debug { "Getting UTC" }
        val string = client.get<String>(GET_SERVER_UTC_URL).also {
            logger.debug { "UTC Get Response: $it" }
        }

        fun throwFailed(): Nothing = throw IllegalArgumentException("Failed to parse UTC, rawString: '$string'")
        val values = jsUTCRegex.find(string)?.groupValues
            ?.drop(1)
            ?.fold(arrayListOf<Int>()) { acc, s ->
                val value = s.toIntOrNull() ?: throw IllegalArgumentException("Failed to parse value: $s")
                acc.add(value)
                acc
            } ?: throwFailed()
        require(values.size == 6) { throwFailed() }
        val utcDate =
            ZonedDateTime.of(values[0], values[1], values[2], values[3], values[4], values[5], 0, ZoneId.of("UTC"))
                ?: throwFailed()
        utcDate.also {
            logger.debug { "UTC Parsed: $it" }
        }
    }

    @JvmName("getServerUTC")
    public suspend fun getServerUTC(client: BiliClient): ZonedDateTime = client.getServerUTC()
}
