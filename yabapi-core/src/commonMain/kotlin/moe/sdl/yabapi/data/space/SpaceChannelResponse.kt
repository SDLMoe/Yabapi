package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class SpaceChannelResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SpaceChannelData? = null,
)


@Serializable
public data class SpaceChannelData(
    @SerialName("count") val count: Int,
    @SerialName("list") val list: List<SpaceChannel> = emptyList(),
)
