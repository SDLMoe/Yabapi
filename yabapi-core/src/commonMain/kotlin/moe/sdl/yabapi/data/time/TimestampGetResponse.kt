package moe.sdl.yabapi.data.time

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode

/**
 * @param code [GeneralCode]
 * @param message 錯誤消息
 * @param ttl 總是爲 1
 * @param data [TimestampGetResponseData]
 * @property timestamp 目前時間, 封裝
 */
@Serializable
public data class TimestampGetResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: TimestampGetResponseData? = null,
) {
    /**
     * 封裝, 直接獲取 [TimestampGetResponseData.timestamp]
     */
    public inline val timestamp: Long?
        get() = data?.timestamp

    /**
     * 封裝, 直接獲取 [TimestampGetResponseData.instant]
     */
    public inline val instant: Instant?
        get() = data?.instant
}

/**
 * @param timestamp 當前時間戳
 */
@Serializable
public data class TimestampGetResponseData(
    @SerialName("now") val timestamp: Long? = null,
) {
    inline val instant: Instant?
        get() = timestamp?.let { Instant.fromEpochSeconds(it) }
}
