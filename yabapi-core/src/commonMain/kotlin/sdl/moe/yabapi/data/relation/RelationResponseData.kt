// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param list 粉絲列表
 * @param total 列表元素個數
 */
@Serializable
public data class RelationResponseData(
    @SerialName("list") val list: List<RelationUserNode>,
    @SerialName("re_version") val reVersion: String? = null,
    @SerialName("total") val total: Int? = null,
)
