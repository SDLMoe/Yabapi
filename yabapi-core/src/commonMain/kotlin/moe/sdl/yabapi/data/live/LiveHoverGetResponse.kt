@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveHoverGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveHover? = null,
)

@Serializable
public data class LiveHover(
    @SerialName("list") val room: List<Room>,
    @SerialName("banner") val banner: List<Banner>,
) {
    @Serializable
    public data class Room(
        @SerialName("area_id") val areaId: Int? = null,
        @SerialName("area_name") val areaName: String? = null,
        @SerialName("pic") val pic: String? = null,
        @SerialName("is_hot") val isHot: Boolean? = null,
        @SerialName("is_new") val isNew: Boolean? = null,
    )
    @Serializable
    public data class Banner(
        @SerialName("id") val id: Int? = null,
        @SerialName("img") val img: String? = null,
        @SerialName("url") val url: String? = null,
    )
}
