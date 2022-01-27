package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import sdl.moe.yabapi.data.video.VideoAttribute
import sdl.moe.yabapi.data.video.VideoCopyright
import sdl.moe.yabapi.data.video.VideoDimension
import sdl.moe.yabapi.data.video.VideoOwner
import sdl.moe.yabapi.data.video.VideoRights
import sdl.moe.yabapi.data.video.VideoStat
import sdl.moe.yabapi.data.video.VideoState
import sdl.moe.yabapi.enums.feed.FeedType
import sdl.moe.yabapi.enums.video.VideoType

@Serializable
public data class VideoCard(
    @SerialName("aid") val aid: Int,
    @SerialName("attribute") val attribute: VideoAttribute,
    @SerialName("cid") val cid: Int,
    @SerialName("copyright") val copyright: VideoCopyright,
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
