package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.serializer.data.info.OfficialRoleSerializer

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
public enum class OfficialRole(public val codes: IntArray) {
    UNKNOWN(intArrayOf()),

    NONE(intArrayOf(0)),

    PERSONAL(intArrayOf(1, 2, 7)),

    ORGANIZATION(intArrayOf(3, 4, 5, 6))
}
