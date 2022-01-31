@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 直播簽到 Response
 */
@Serializable
public data class LiveSignResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveSignData? = null,
)

/**
 * 簽到數據
 * @property text 提示
 * @property specialText 特殊提示
 * @property allDays 本月一共多少天
 * @property hadSignDays 簽到過的時間
 * @property isBonusDay 是否獎勵日
 */
@Serializable
public data class LiveSignData(
    @SerialName("text") val text: String? = null,
    @SerialName("specialText") val specialText: String? = null,
    @SerialName("allDays") val allDays: Int? = null,
    @SerialName("hadSignDays") val hadSignDays: Int? = null,
    @SerialName("isBonusDay") val isBonusDay: Boolean? = null,
)
