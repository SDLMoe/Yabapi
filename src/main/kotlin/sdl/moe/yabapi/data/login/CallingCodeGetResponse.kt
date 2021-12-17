package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode

/**
 * 获取国际电话区号的响应
 * @property code 状态码
 * @property data [CallingCodeGetResponseData]
 */
@Serializable
public data class CallingCodeGetResponse(
    @SerialName("code")
    val code: GeneralCode,
    @SerialName("data")
    val data: CallingCodeGetResponseData,
)

/**
 * @param commons 常用区号
 * @param others 其他区号
 * @see [CallingCodeNode]
 */
@Serializable
public data class CallingCodeGetResponseData(
    @SerialName("commons")
    val commons: List<CallingCodeNode>,
    @SerialName("others")
    val others: List<CallingCodeNode>,
)

/**
 * @param callingCodeValue 国际代码值, 如: 86
 * @param name 国际代码名称, 如: 中国
 * @param callingCode 国际代码, 如: +86
 */
@Serializable
public data class CallingCodeNode(
    @SerialName("id")
    val callingCodeValue: String,
    @SerialName("cname")
    val name: String,
    @SerialName("country_id")
    val callingCode: String,
)
