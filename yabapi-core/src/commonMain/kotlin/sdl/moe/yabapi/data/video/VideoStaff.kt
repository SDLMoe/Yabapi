// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.info.Official
import sdl.moe.yabapi.data.info.Vip

@Serializable
public data class VideoStaff(
    @SerialName("mid") val mid: Int,
    @SerialName("title") val title: String,
    @SerialName("name") val name: String,
    @SerialName("face") val face: String,
    @SerialName("vip") val vip: Vip,
    @SerialName("official") val official: Official,
    @SerialName("follower") val follower: Int,
    @SerialName("label_style") val labelStyle: Int? = null,
)
