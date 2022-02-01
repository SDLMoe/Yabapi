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
    @SerialName("status") val status: Boolean? = null,
    @SerialName("data") private val _data: JsonElement? = null,
) {
    val data: SubscribedTagData? by lazy {
        if (status == true) _data?.let { Yabapi.defaultJson.value.decodeFromJsonElement(it) } else null
    }

    val failedMsg: String? by lazy {
        if (status == false) _data?.jsonPrimitive?.contentOrNull else null
    }
}

@Serializable
public data class SubscribedTagData(
    @SerialName("tags") val tags: List<SubscribedTag> = emptyList(),
    @SerialName("count") val count: Int? = null,
)

@Serializable
public data class SubscribedTag(
    @SerialName("name") val name: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("tag_id") val tagId: Int? = null,
    @SerialName("notify") val notify: Int? = null,
    @SerialName("archive_count") val archiveCount: Int? = null,
    @SerialName("updated_ts") val updatedTimestamp: String? = null,
)
