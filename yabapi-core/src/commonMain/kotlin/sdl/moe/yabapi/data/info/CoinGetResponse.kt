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
