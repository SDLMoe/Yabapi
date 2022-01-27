@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import sdl.moe.yabapi.Yabapi.defaultJson
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 *
 */
@Serializable
public data class SpaceSettingResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("data") private val _data: JsonElement? = null,
) {
    val dataWhenTrue: SpaceSetting? by lazy {
        if (status) _data?.let { defaultJson.value.decodeFromJsonElement(it) } else null
    }

    val dataWhenFalse: String? by lazy {
        if (!status) _data?.jsonPrimitive?.contentOrNull else null
    }
}

@Serializable
public data class SpaceSetting(
    @SerialName("privacy") val privacy: SpacePrivacy,
    @SerialName("show_nft_switch") val showNftSwitch: Boolean,
    @SerialName("index_order") val indexOrder: List<SpacePosNode> = emptyList(),
    @SerialName("theme") val theme: String,
    @SerialName("theme_preview_img_path") val themePreviewImgPath: String,
    @SerialName("toutu") val banner: SpaceBanner,
)

@Serializable
public data class SpacePrivacy(
    @SerialName("bangumi") val bangumi: Boolean,
    @SerialName("bbq") val bbq: Boolean,
    @SerialName("channel") val channel: Boolean,
    @SerialName("close_space_medal") val closeSpaceMedal: Boolean,
    @SerialName("coins_video") val coinsVideo: Boolean,
    @SerialName("comic") val comic: Boolean,
    @SerialName("disable_following") val disableFollowing: Boolean,
    @SerialName("disable_show_nft") val disableShowNft: Boolean? = null,
    @SerialName("disable_show_school") val disableShowSchool: Boolean,
    @SerialName("dress_up") val dressUp: Boolean,
    @SerialName("fav_video") val favVideo: Boolean,
    @SerialName("groups") val groups: Boolean,
    @SerialName("likes_video") val likeVideo: Boolean,
    @SerialName("live_playback") val livePlayback: Boolean,
    @SerialName("only_show_wearing") val onlyShowWearing: Boolean,
    @SerialName("played_game") val playedGame: Boolean,
    @SerialName("tags") val tags: Boolean,
    @SerialName("user_info") val userInfo: Boolean,
)

@Serializable
public data class SpacePosNode(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
)

@Serializable
public data class SpaceBanner(
    @SerialName("sid") val sid: Int,
    @SerialName("expire") val expire: Long,
    @SerialName("s_img") val smallImg: String,
    @SerialName("l_img") val largeImg: String,
    @SerialName("android_img") val androidImg: String,
    @SerialName("iphone_img") val iPhoneImg: String,
    @SerialName("ipad_img") val iPadImg: String,
    @SerialName("thumbnail_img") val thumbnailImg: String,
    @SerialName("platform") val platform: Int,
)
