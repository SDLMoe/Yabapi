package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

/**
 * 獲取硬幣變化的返回
 * @param code [GeneralCode]
 * @param data [CoinLog]
 */
@Serializable
public data class CoinLogGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: CoinLog? = null,
)

/**
 * 硬幣變化記錄
 */
@Serializable
public data class CoinLog(
    @SerialName("list") val list: List<CoinLogNode> = listOf(),
    @SerialName("count") val size: Int,
)

/**
 * @param time 時間, YYYY-MM-DD HH:MM:SS
 * @param changed 變化量 正值收入, 負值支出
 * @param reason 變化說明
 */
@Serializable
public data class CoinLogNode(
    @SerialName("time") val time: String,
    @SerialName("delta") val changed: Double,
    @SerialName("reason") val reason: String,
)
