// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoTagsGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<VideoTag> = emptyList(),
)

@Serializable
public data class VideoTag(
    @SerialName("tag_id") val id: Int,
    @SerialName("tag_name") val name: String,
    @SerialName("cover") val cover: String,
    @SerialName("head_cover") val headCover: String,
    @SerialName("content") val content: String,
    @SerialName("short_content") val shortContent: String,
    @SerialName("type") val type: Int,
    @SerialName("state") val state: Int,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("count") val count: VideoTagCount,
    @SerialName("is_atten") val isSubscribed: Boolean,
    @SerialName("likes") val likes: Int,
    @SerialName("hates") val hates: Int,
    @SerialName("attribute") val attribute: Int,
    @SerialName("liked") val liked: Boolean,
    @SerialName("hated") val hated: Boolean,
    @SerialName("extra_attr") val extraAttribute: Int,
)

@Serializable
public data class VideoTagCount(
    @SerialName("view") val view: Int,
    @SerialName("use") val used: Int,
    @SerialName("atten") val subscribec: Int,
)
