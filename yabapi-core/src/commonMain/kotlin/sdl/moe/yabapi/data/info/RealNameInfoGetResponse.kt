@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 * 实名状态返回
 * @param code [GeneralCode]
 * @param message 错误信息
 * @param data [RealNameStatus]
 */
@Serializable
public data class RealNameInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RealNameStat? = null,
)

/**
 * @param status 是否实名
 */
@Serializable
public data class RealNameStat(
    @SerialName("status") val status: Boolean,
)
