@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import moe.sdl.yabapi.Yabapi.defaultJson
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 *
 */
@Serializable
public data class SpaceSettingResponse(
    @SerialName("status") val status: Boolean? = null,
    @SerialName("data") private val _data: JsonElement? = null,
) {
    val dataWhenTrue: SpaceSetting? by lazy {
        if (status == true) _data?.let { defaultJson.value.decodeFromJsonElement(it) } else null
    }

    val dataWhenFalse: String? by lazy {
        if (status == false) _data?.jsonPrimitive?.contentOrNull else null
    }
}

@Serializable
public data class SpaceSetting(
    @SerialName("privacy") val privacy: SpacePrivacy? = null,
    @SerialName("show_nft_switch") val showNftSwitch: Boolean? = null,
    @SerialName("index_order") val indexOrder: List<SpacePosNode> = emptyList(),
    @SerialName("theme") val theme: String? = null,
    @SerialName("theme_preview_img_path") val themePreviewImgPath: String? = null,
    @SerialName("toutu") val banner: SpaceBanner? = null,
)

@Serializable
public data class SpacePrivacy(
    @SerialName("bangumi") val bangumi: Boolean? = null,
    @SerialName("bbq") val bbq: Boolean? = null,
    @SerialName("channel") val channel: Boolean? = null,
    @SerialName("close_space_medal") val closeSpaceMedal: Boolean? = null,
    @SerialName("coins_video") val coinsVideo: Boolean? = null,
    @SerialName("comic") val comic: Boolean? = null,
    @SerialName("disable_following") val disableFollowing: Boolean? = null,
    @SerialName("disable_show_nft") val disableShowNft: Boolean? = null,
    @SerialName("disable_show_school") val disableShowSchool: Boolean? = null,
    @SerialName("dress_up") val dressUp: Boolean? = null,
    @SerialName("fav_video") val favVideo: Boolean? = null,
    @SerialName("groups") val groups: Boolean? = null,
    @SerialName("likes_video") val likeVideo: Boolean? = null,
    @SerialName("live_playback") val livePlayback: Boolean? = null,
    @SerialName("only_show_wearing") val onlyShowWearing: Boolean? = null,
    @SerialName("played_game") val playedGame: Boolean? = null,
    @SerialName("tags") val tags: Boolean? = null,
    @SerialName("user_info") val userInfo: Boolean? = null,
)

@Serializable
public data class SpacePosNode(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class SpaceBanner(
    @SerialName("sid") val sid: Int? = null,
    @SerialName("expire") val expire: Long? = null,
    @SerialName("s_img") val smallImg: String? = null,
    @SerialName("l_img") val largeImg: String? = null,
    @SerialName("android_img") val androidImg: String? = null,
    @SerialName("iphone_img") val iPhoneImg: String? = null,
    @SerialName("ipad_img") val iPadImg: String? = null,
    @SerialName("thumbnail_img") val thumbnailImg: String? = null,
    @SerialName("platform") val platform: Int? = null,
)
