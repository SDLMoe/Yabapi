@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoTagsGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<VideoTag> = emptyList(),
)

@Serializable
public data class VideoTag(
    @SerialName("tag_id") val id: Long? = null,
    @SerialName("tag_name") val name: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("head_cover") val headCover: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("short_content") val shortContent: String? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("count") val count: VideoTagCount? = null,
    @SerialName("is_atten") val isSubscribed: Boolean? = null,
    @SerialName("likes") val likes: Int? = null,
    @SerialName("hates") val hates: Int? = null,
    @SerialName("attribute") val attribute: Int? = null,
    @SerialName("liked") val liked: Boolean? = null,
    @SerialName("hated") val hated: Boolean? = null,
    @SerialName("extra_attr") val extraAttribute: Int? = null,
)

@Serializable
public data class VideoTagCount(
    @SerialName("view") val view: Int? = null,
    @SerialName("use") val used: Int? = null,
    @SerialName("atten") val subscribes: Int? = null,
)
