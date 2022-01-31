package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class VideoOnlineGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoOnline? = null,
)

@Serializable
public data class VideoOnline(
    @SerialName("total") val all: String? = null,
    @SerialName("count") val web: String? = null,
    @SerialName("show_switch") val showSwitch: OnlineShowSwitch? = null,
    @SerialName("abtest") val abtest: AbTest? = null,
)

@Serializable
public data class OnlineShowSwitch(
    @SerialName("total") val showTotal: Boolean? = null,
    @SerialName("count") val showWeb: Boolean? = null,
)

@Serializable
public data class AbTest(
    @SerialName("group") val group: String? = null,
)
