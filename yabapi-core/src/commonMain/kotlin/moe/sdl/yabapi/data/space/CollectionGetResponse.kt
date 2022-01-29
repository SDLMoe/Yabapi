package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import moe.sdl.yabapi.data.GeneralCode

@Serializable
public data class CollectionGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: CollectionData? = null,
)

@Serializable
public data class CollectionData(
    @SerialName("count") val count: Int,
    @SerialName("list") val list: List<CollectionItem> = emptyList(),
    @SerialName("season") val season: JsonElement? = null,
)
