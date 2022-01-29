package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class LiveSignLastMonthResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: LiveSignLastMonthInfo,
)

@Serializable
public data class LiveSignLastMonthInfo(
    @SerialName("month") val month: Int,
    @SerialName("days") val days: Int,
    @SerialName("hadSignDays") val hadSignDays: Int,
    @SerialName("signDaysList") val signDaysList: List<Int>,
    @SerialName("signBonusDaysList") val signBonusDaysList: List<Int>,
)
