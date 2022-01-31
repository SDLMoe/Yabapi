package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.data.video.VideoAttribute
import moe.sdl.yabapi.data.video.VideoCopyright
import moe.sdl.yabapi.data.video.VideoCopyright.UNKNOWN
import moe.sdl.yabapi.data.video.VideoDimension
import moe.sdl.yabapi.data.video.VideoOwner
import moe.sdl.yabapi.data.video.VideoRights
import moe.sdl.yabapi.data.video.VideoStat
import moe.sdl.yabapi.data.video.VideoState
import moe.sdl.yabapi.enums.feed.FeedType
import moe.sdl.yabapi.enums.video.VideoType

@Serializable
public data class CollectionCard(
    @SerialName("aid") val aid: String? = null,
    @SerialName("attribute") val attribute: VideoAttribute? = null,
    @SerialName("attribute_v2") val attributeV2: VideoAttribute? = null,
    @SerialName("cid") val cid: Int? = null,
    @SerialName("collection") val collection: CollectionInfo? = null,
    @SerialName("copyright") val copyright: VideoCopyright = UNKNOWN,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("desc") val description: String? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("dynamic") val dynamic: String? = null,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("jump_url") val jumpUrl: String? = null,
    @SerialName("owner") val owner: VideoOwner? = null,
    @SerialName("pic") val pic: String? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("rights") val rights: VideoRights? = null,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("season_theme") val seasonTheme: SeasonTheme? = null,
    @SerialName("share_subtitle") val shareSubtitle: String? = null,
    @SerialName("short_link") val shortLink: String? = null,
    @SerialName("short_link_v2") val shortLinkV2: String? = null,
    @SerialName("stat") val stat: VideoStat? = null,
    @SerialName("state") val state: VideoState = VideoState.UNKNOWN,
    @SerialName("tid") val type: VideoType? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("tname") val typeName: String? = null,
    @SerialName("videos") val videos: Int? = null,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.COLLECTION.code
        override fun decode(json: Json, data: String): CollectionCard = json.decodeFromString(data)
    }
}

@Serializable
public data class CollectionInfo(
    @SerialName("cover") val cover: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("title") val title: String? = null,
)

@Serializable
public data class SeasonTheme(
    @SerialName("bg_color") private val _bgColor: String? = null,
    @SerialName("selected_bg_color") private val _selectedBgColor: String? = null,
    @SerialName("text_color") private val _textColor: String? = null,
) {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun String.toRgb() = if (this.isNotBlank()) RgbColor.fromHex("#$this") else null

    val backgroundColor: RgbColor? by lazy { _bgColor?.toRgb() }
    val selectedBackgroundColor: RgbColor? by lazy { _selectedBgColor?.toRgb() }
    val textColor: RgbColor? by lazy { _textColor?.toRgb() }
}
