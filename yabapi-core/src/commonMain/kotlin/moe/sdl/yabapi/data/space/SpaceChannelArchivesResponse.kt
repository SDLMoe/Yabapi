package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class SpaceChannelArchivesResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SpaceChannelArchivesData? = null,
)

@Serializable
public data class SpaceChannelArchivesData(
    @SerialName("episodic_button") val button: SpaceVideoButton,
    @SerialName("list") val list: SpaceChannel,
    @SerialName("page") val page: SpaceChannelPage,
)

@Serializable
public data class SpaceChannelPage(
    @SerialName("count") val count: Int,
    @SerialName("num") val num: Int,
    @SerialName("size") val size: Int,
)
