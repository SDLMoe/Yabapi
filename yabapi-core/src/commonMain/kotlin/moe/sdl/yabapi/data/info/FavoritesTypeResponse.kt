package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class FavoritesTypeResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<FavoritesType> = emptyList(),
)

@Serializable
public data class FavoritesType(
    @SerialName("tid") val tid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("count") val count: Int? = null,
)
