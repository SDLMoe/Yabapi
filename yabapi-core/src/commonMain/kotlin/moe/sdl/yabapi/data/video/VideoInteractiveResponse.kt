@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoInteractiveResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoInteractiveData? = null,
)

@Serializable
public data class VideoInteractiveData(
    @SerialName("title") val title: String? = null,
    @SerialName("edge_id") val edgeId: Long? = null,
    @SerialName("story_list") val storyList: List<StoryNode> = emptyList(),
    @SerialName("edges") val edges: EdgesInfo? = null,
    @SerialName("buvid") val buvid: String? = null,
    @SerialName("preload") val preload: InteractionPreload? = null,
    @SerialName("is_leaf") val isLeaf: Boolean? = null,
)

@Serializable
public data class StoryNode(
    @SerialName("node_id") val nodeId: Long? = null,
    @SerialName("edge_id") val edgeId: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cid") val cid: Long? = null,
    @SerialName("start_pos") val startPos: Int? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("is_current") val isCurrent: Boolean? = null,
    @SerialName("hidden_vars") val hiddenVars: List<BranchVars>? = null,
    @SerialName("cursor") val cursor: Int? = null,
)

@Serializable
public data class EdgesInfo(
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("questions") val questions: List<Question> = emptyList(),
    @SerialName("skin") val skin: Skin? = null,
) {
    @Serializable
    public data class Question(
        @SerialName("id") val id: Long? = null,
        @SerialName("type") val type: Int? = null,
        @SerialName("start_time_r") val startTimeR: Int? = null,
        @SerialName("duration") val duration: Int? = null,
        @SerialName("pause_video") val pauseVideo: Boolean? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("choices") val choices: List<Choice> = emptyList(),
    )

    @Serializable
    public data class Choice(
        @SerialName("id") val id: Long? = null,
        @SerialName("platform_action") val platformAction: String? = null,
        @SerialName("native_action") val nativeAction: String? = null,
        @SerialName("condition") val condition: String? = null,
        @SerialName("cid") val cid: Long? = null,
        @SerialName("x") val x: Int? = null,
        @SerialName("y") val y: Int? = null,
        @SerialName("text_align") val textAlign: Int? = null,
        @SerialName("option") val option: String? = null,
        @SerialName("is_default") val isDefault: Boolean? = null,
        @SerialName("is_hidden") val isHidden: Boolean? = null,
    )

    @Serializable
    public data class Skin(
        @SerialName("choice_image") val choiceImage: String? = null,
        @SerialName("title_text_color") val titleTextColor: String? = null,
        @SerialName("title_shadow_color") val titleShadowColor: String? = null,
        @SerialName("title_shadow_offset_y") val titleShadowOffsetY: Int? = null,
        @SerialName("title_shadow_radius") val titleShadowRadius: Int? = null,
        @SerialName("progressbar_color") val progressbarColor: String? = null,
        @SerialName("progressbar_shadow_color") val progressbarShadowColor: String? = null,
    )
}

@Serializable
public data class InteractionPreload(
    @SerialName("video") val videos: List<Video>? = null,
) {
    @Serializable
    public data class Video(
        @SerialName("aid") val aid: Long? = null,
        @SerialName("cid") val cid: Long? = null,
    )
}

@Serializable
public data class BranchVars(
    @SerialName("value") val value: Int? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("id_v2") val idV2: String? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("is_show") val isShow: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("skip_overwrite") val skipOverwrite: Boolean? = null,
)
