package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class LiveSignInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveSignInfo,
)

@Serializable
public data class LiveSignInfo(
    @SerialName("text") val text: String,
    @SerialName("specialText") val specialText: String,
    @SerialName("status") val status: Int,
    @SerialName("allDays") val allDays: Int,
    @SerialName("curMonth") val curMonth: Int,
    @SerialName("curYear") val curYear: Int,
    @SerialName("curDay") val curDay: Int,
    @SerialName("curDate") val curDate: String,
    @SerialName("hadSignDays") val hadSignDays: Int,
    @SerialName("newTask") val newTask: Int,
    @SerialName("signDaysList") val signDaysList: List<Int> = emptyList(),
    @SerialName("signBonusDaysList") val signBonusDaysList: List<Int> = emptyList(),
)
