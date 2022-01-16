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
