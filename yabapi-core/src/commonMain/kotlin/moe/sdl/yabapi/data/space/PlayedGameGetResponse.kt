package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.space.SpaceResponseCode.UNKNOWN

@Serializable
public data class PlayedGameGetResponse(
    @SerialName("code") val code: SpaceResponseCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<PlayedGameNode>? = null,
)



@Serializable
public data class PlayedGameNode(
    @SerialName("website") val website: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("name") val name: String? = null,
)
