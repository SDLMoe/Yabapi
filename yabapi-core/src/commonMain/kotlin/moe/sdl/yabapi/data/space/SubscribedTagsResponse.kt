package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import moe.sdl.yabapi.Yabapi

@Serializable
public data class SubscribedTagsResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("data") private val _data: JsonElement,
) {
    val data: SubscribedTagData? by lazy {
        if (status) Yabapi.defaultJson.value.decodeFromJsonElement(_data) else null
    }

    val failedMsg: String? by lazy {
        if (!status) _data.jsonPrimitive.contentOrNull else null
    }
}

@Serializable
public data class SubscribedTagData(
    @SerialName("tags") val tags: List<SubscribedTag>,
    @SerialName("count") val count: Int,
)

@Serializable
public data class SubscribedTag(
    @SerialName("name") val name: String,
    @SerialName("cover") val cover: String,
    @SerialName("tag_id") val tagId: Int,
    @SerialName("notify") val notify: Int,
    @SerialName("archive_count") val archiveCount: Int,
    @SerialName("updated_ts") val updatedTimestamp: String,
)
