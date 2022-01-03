// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param data 特別關注 mid list
 */
@Serializable
public data class SpecialFollowingQueryResponse(
    @SerialName("code") val code: RelationResponseCode = RelationResponseCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<Int> = emptyList(),
)
