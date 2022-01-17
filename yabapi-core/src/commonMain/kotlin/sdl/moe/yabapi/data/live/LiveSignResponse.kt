@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 * 直播簽到 Response
 */
@Serializable
public data class LiveSignResponse(
    @SerialName("code") val code: GeneralCode,
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
    @SerialName("text") val text: String,
    @SerialName("specialText") val specialText: String,
    @SerialName("allDays") val allDays: Int,
    @SerialName("hadSignDays") val hadSignDays: Int,
    @SerialName("isBonusDay") val isBonusDay: Boolean,
)
