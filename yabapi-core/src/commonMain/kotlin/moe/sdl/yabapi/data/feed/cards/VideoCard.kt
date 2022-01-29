package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
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
public data class VideoCard(
    @SerialName("aid") val aid: Int,
    @SerialName("attribute") val attribute: VideoAttribute,
    @SerialName("attribute_v2") val attributeV2: Int? = null,
    @SerialName("cid") val cid: Int,
    @SerialName("comment_jump_url") val commentJumpUrl: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright = UNKNOWN,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("desc") val desc: String,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("duration") val duration: Long,
    @SerialName("dynamic") val feed: String,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("jump_url") val jumpUrl: String,
    @SerialName("mission_id") val missionId: Int? = null,
    @SerialName("owner") val owner: VideoOwner,
    @SerialName("pic") val pic: String,
    @SerialName("player_info") val playerInfo: JsonElement? = null,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("redirect_url") val redirectUrl: String? = null,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("share_subtitle") val shareSubtitle: String? = null,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("short_link") val shortLink: String,
    @SerialName("short_link_v2") val shortLinkV2: String,
    @SerialName("stat") val stat: VideoStat,
    @SerialName("state") val state: VideoState,
    @SerialName("tid") val type: VideoType,
    @SerialName("title") val title: String,
    @SerialName("tname") val typeName: String,
    @SerialName("up_from_v2") val upFromV2: Int? = null,
    @SerialName("videos") val videos: Int,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.VIDEO.code

        override fun decode(json: Json, data: String): VideoCard = json.decodeFromString(data)
    }
}
