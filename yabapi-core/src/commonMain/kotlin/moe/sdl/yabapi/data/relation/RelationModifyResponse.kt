package moe.sdl.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RelationModifyResponse(
    @SerialName("code") val code: RelationModifyCode,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
)

@Serializable
public data class RelationBatchModifyResponse(
    @SerialName("code") val code: RelationModifyCode,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RelationBatchModifyData? = null,
)

@Serializable
public data class RelationBatchModifyData(
    @SerialName("failed_fids") val failedId: List<String>,
)
