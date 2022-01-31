package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class CollectionFavGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: CollectionFavData? = null,
)

@Serializable
public data class CollectionFavData(
    @SerialName("count") val count: Int? = null,
    @SerialName("list") val list: List<FavCollectionItem> = emptyList(),
    @SerialName("has_more") val hasMore: Boolean? = null,
)
