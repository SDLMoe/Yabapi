// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode

@Serializable
public data class VideoOnlineGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoOnline? = null,
)

@Serializable
public data class VideoOnline(
    @SerialName("total") val all: String,
    @SerialName("count") val web: String,
    @SerialName("show_switch") val showSwitch: OnlineShowSwitch,
    @SerialName("abtest") val abtest: AbTest? = null,
)

@Serializable
public data class OnlineShowSwitch(
    @SerialName("total") val showTotal: Boolean,
    @SerialName("count") val showWeb: Boolean,
)

@Serializable
public data class AbTest(
    @SerialName("group") val group: String,
)
