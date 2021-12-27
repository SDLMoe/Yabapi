// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 * 获得硬币数返回
 * @param data [CoinData]
 */
@Serializable
public data class CoinGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("status") val status: Boolean,
    @SerialName("data") val data: CoinData,
)

/**
 * @param money 硬币数
 */
@Serializable
public data class CoinData(
    @SerialName("money") val money: Double? = null,
)
