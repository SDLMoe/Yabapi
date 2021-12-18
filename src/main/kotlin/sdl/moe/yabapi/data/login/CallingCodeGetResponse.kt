// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

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
 * @param common 常用区号
 * @param others 其他区号
 * @property all 返回全部区号
 * @see [CallingCodeNode]
 */
@Serializable
public data class CallingCodeGetResponseData(
    @SerialName("common")
    val common: List<CallingCodeNode>,
    @SerialName("others")
    val others: List<CallingCodeNode>,
) {
    val all: List<CallingCodeNode> = common + others
}

/**
 * @param id ID, 如: 1
 * @param name 名称, 如: 中国大陆
 * @param callingCode 国际区码, 如: 86
 */
@Serializable
public data class CallingCodeNode(
    @SerialName("id")
    val id: String,
    @SerialName("cname")
    val name: String,
    @SerialName("country_id")
    val callingCode: String,
)
