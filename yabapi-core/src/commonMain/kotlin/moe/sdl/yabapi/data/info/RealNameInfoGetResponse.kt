@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

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
