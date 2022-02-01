package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class LiveSignLastMonthResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveSignLastMonthInfo? = null,
)

@Serializable
public data class LiveSignLastMonthInfo(
    @SerialName("month") val month: Int? = null,
    @SerialName("days") val days: Int? = null,
    @SerialName("hadSignDays") val hadSignDays: Int? = null,
    @SerialName("signDaysList") val signDaysList: List<Int> = emptyList(),
    @SerialName("signBonusDaysList") val signBonusDaysList: List<Int> = emptyList(),
)
