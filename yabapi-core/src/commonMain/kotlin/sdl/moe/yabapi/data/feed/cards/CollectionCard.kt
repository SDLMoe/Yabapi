package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.data.RgbColor
import sdl.moe.yabapi.data.video.VideoAttribute
import sdl.moe.yabapi.data.video.VideoCopyright
import sdl.moe.yabapi.data.video.VideoCopyright.UNKNOWN
import sdl.moe.yabapi.data.video.VideoDimension
import sdl.moe.yabapi.data.video.VideoOwner
import sdl.moe.yabapi.data.video.VideoRights
import sdl.moe.yabapi.data.video.VideoStat
import sdl.moe.yabapi.data.video.VideoState
import sdl.moe.yabapi.enums.feed.FeedType
import sdl.moe.yabapi.enums.video.VideoType

@Serializable
public data class CollectionCard(
    @SerialName("aid") val aid: String,
    @SerialName("attribute") val attribute: VideoAttribute,
    @SerialName("attribute_v2") val attributeV2: VideoAttribute,
    @SerialName("cid") val cid: Int,
    @SerialName("collection") val collection: CollectionInfo,
    @SerialName("copyright") val copyright: VideoCopyright = UNKNOWN,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("desc") val description: String,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("duration") val duration: Long,
    @SerialName("dynamic") val dynamic: String,
    @SerialName("first_frame") val firstFrame: String,
    @SerialName("jump_url") val jumpUrl: String,
    @SerialName("owner") val owner: VideoOwner,
    @SerialName("pic") val pic: String,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("season_id") val seasonId: Int,
    @SerialName("season_theme") val seasonTheme: SeasonTheme,
    @SerialName("share_subtitle") val shareSubtitle: String,
    @SerialName("short_link") val shortLink: String,
    @SerialName("short_link_v2") val shortLinkV2: String,
    @SerialName("stat") val stat: VideoStat,
    @SerialName("state") val state: VideoState = VideoState.UNKNOWN,
    @SerialName("tid") val type: VideoType,
    @SerialName("title") val title: String,
    @SerialName("tname") val typeName: String,
    @SerialName("videos") val videos: Int,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.COLLECTION.code
        override fun decode(json: Json, data: String): CollectionCard = json.decodeFromString(data)
    }
}

@Serializable
public data class CollectionInfo(
    @SerialName("cover") val cover: String,
    @SerialName("id") val id: Int,
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("title") val title: String,
)

@Serializable
public data class SeasonTheme(
    @SerialName("bg_color") private val _bgColor: String,
    @SerialName("selected_bg_color") private val _selectedBgColor: String,
    @SerialName("text_color") private val _textColor: String,
) {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun String.toRgb() = RgbColor.fromHex("#$this")

    val backgroundColor: RgbColor by lazy { _bgColor.toRgb() }
    val selectedBackgroundColor: RgbColor by lazy { _selectedBgColor.toRgb() }
    val textColor: RgbColor by lazy { _textColor.toRgb() }
}
