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
    @SerialName("data") val data: TimestampGetResponseData,
) {
    /**
     * 封裝, 直接獲取 [TimestampGetResponseData.timestamp]
     */
    public inline val timestamp: Long
        get() = data.timestamp

    /**
     * 封裝, 直接獲取 [TimestampGetResponseData.instant]
     */
    public inline val instant: Instant
        get() = data.instant
}

/**
 * @param timestamp 當前時間戳
 */
@Serializable
public data class TimestampGetResponseData(
    @SerialName("now") val timestamp: Long,
) {
    inline val instant: Instant
        get() = Instant.fromEpochSeconds(timestamp)
}
