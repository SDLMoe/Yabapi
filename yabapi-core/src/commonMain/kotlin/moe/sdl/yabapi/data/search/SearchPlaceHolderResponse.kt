package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class SearchPlaceHolderResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SearchPlaceHolder? = null,
)

@Serializable
public data class SearchPlaceHolder(
    @SerialName("seid") val seid: String? = null,
    @SerialName("id") val id: ULong? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("show_name") val showName: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("goto_type") val gotoType: Int? = null,
    @SerialName("goto_value") val gotoValue: String? = null,
    @SerialName("url") val url: String? = null,
)
