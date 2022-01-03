// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserNameplateData(
    @SerialName("nid") val nid: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("image_small") val smallImage: String,
    @SerialName("level") val level: String,
    @SerialName("condition") val condition: String,
)
