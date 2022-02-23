package moe.sdl.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 關係查詢返回
 * @param data [RelationUserNode]
 */
@Serializable
public data class RelationQueryResponse(
    @SerialName("code") val code: RelationResponseCode = RelationResponseCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RelationUserNode? = null,
)

/**
 * 批量關係查詢返回
 * @param dataMap 數據返回, key 爲 uid
 */
@Serializable
public data class RelationQueryBatchResponse(
    @SerialName("code") val code: RelationResponseCode = RelationResponseCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val dataMap: Map<String, RelationUserNode> = emptyMap(),
) {
    val data: List<RelationUserNode> by lazy {
        dataMap.values.toList()
    }
}

/**
 * 查詢互相關係返回
 * @param data [RelationQueryMutuallyData]
 */
@Serializable
public data class RelationQueryMutuallyResponse(
    @SerialName("code") val code: RelationResponseCode = RelationResponseCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RelationQueryMutuallyData? = null,
)

/**
 * @param relation 目標用戶 -> 當前用戶 的關係
 * @param beRelation 當前用戶 -> 目標用戶 的關係
 */
@Serializable
public data class RelationQueryMutuallyData(
    @SerialName("relation") val relation: RelationUserNode? = null,
    @SerialName("be_relation") val beRelation: RelationUserNode? = null,
)
