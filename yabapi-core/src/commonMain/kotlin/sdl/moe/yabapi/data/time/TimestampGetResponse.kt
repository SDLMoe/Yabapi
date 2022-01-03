// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.time

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode

/**
 * @param code [GeneralCode]
 * @param message 錯誤消息
 * @param ttl 總是爲 1
 * @param data [TimestampGetResponseData]
 * @property timestamp 目前時間, 封裝
 */
@Serializable
public data class TimestampGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: TimestampGetResponseData
) {
    /**
     * 封裝, 直接獲取 [TimestampGetResponseData.timestamp]
     */
    public val timestamp: Long
        get() = data.timestamp

    /**
     * 封裝, 直接獲取 [TimestampGetResponseData.instant]
     */
    public val instant: Instant = data.instant
}

/**
 * @param timestamp 當前時間戳
 */
@Serializable
public data class TimestampGetResponseData(
    @SerialName("now") val timestamp: Long
) {
    val instant: Instant = Instant.fromEpochSeconds(timestamp)
}
