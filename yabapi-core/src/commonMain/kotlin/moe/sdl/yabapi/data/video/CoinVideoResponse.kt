package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.video.CoinVideoCode.UNKNOWN

@Serializable
public data class CoinVideoResponse(
    @SerialName("code") val code: CoinVideoCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: CoinVideoData? = null,
)

@Serializable
public data class CoinVideoData(
    @SerialName("like") val isLikeSucceed: Boolean? = null,
)

@Serializable
public enum class CoinVideoCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-102")
    BANNED,

    @SerialName("-104")
    COIN_NOT_ENOUGH,

    @SerialName("-111")
    CSRF_ERROR,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("10003")
    NO_SUCH_VIDEO,

    @SerialName("34002")
    COIN_SELF,

    @SerialName("34003")
    INVALID_COUNT,

    @SerialName("34004")
    INTERVAL_TOO_SHORT,

    @SerialName("34005")
    LIMITED,
}
