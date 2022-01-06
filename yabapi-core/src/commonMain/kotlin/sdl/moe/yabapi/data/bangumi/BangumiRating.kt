// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BangumiRating(
    @SerialName("count") val count: Int,
    @SerialName("score") val score: Double,
)
