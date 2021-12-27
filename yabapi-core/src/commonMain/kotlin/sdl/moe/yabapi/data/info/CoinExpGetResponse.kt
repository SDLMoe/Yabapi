// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode

/**
 * 实时获取投币经验值
 * @param code 返回值 [GeneralCode]
 * @param coinExp 每日投币经验值, 上限 50
 * @see ExpReward
 */
@Serializable
public data class CoinExpGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String,
    @SerialName("number") val coinExp: Int,
)
