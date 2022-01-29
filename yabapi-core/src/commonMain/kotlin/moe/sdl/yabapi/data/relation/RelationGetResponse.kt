package moe.sdl.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 獲取關係的返回
 * @param code 返回值 [RelationResponseCode]
 * @param data [RelationResponseData]
 */
@Serializable
public data class RelationGetResponse(
    @SerialName("code") val code: RelationResponseCode = RelationResponseCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RelationResponseData? = null,
)
