// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.info.OfficialRoleSerializer

/**
 * 官方身份數據類
 * @param role [OfficialRole]
 * @param title 認證信息
 * @param info 備註
 * @param isCertified 是否認證
 * @see [IsOfficialCertified]
 */
@Serializable
public data class Official(
    @SerialName("role") val role: OfficialRole = OfficialRole.UNKNOWN,
    @SerialName("title") val title: String? = null,
    @SerialName("desc") val info: String? = null,
    @SerialName("type") val isCertified: IsOfficialCertified = IsOfficialCertified(false),
)

@Serializable(with = OfficialRoleSerializer::class)
public enum class OfficialRole(public val valueList: List<Int>) {
    UNKNOWN(emptyList()),

    NONE(listOf(0)),

    PERSONAL(listOf(1, 2, 7)),

    ORGANIZATION(listOf(3, 4, 5, 6))
}
