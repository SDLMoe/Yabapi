@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.live.GuardLevel
import moe.sdl.yabapi.data.live.WatchedShow
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class UserSpaceGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: UserSpace? = null,
)

@Serializable
public data class UserSpace(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("face_nft") val avatarNft: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("rank") val rank: Int? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("jointime") val joinTime: Long? = null,
    @SerialName("moral") val moral: Int? = null,
    @SerialName("silence") val isBanned: Boolean? = null,
    @SerialName("coins") val coins: Int? = null,
    @SerialName("fans_badge") val hasFansBadge: Boolean? = null,
    @SerialName("fans_medal") val fansMedal: UserFansMedal? = null,
    @SerialName("official") val official: Official? = null,
    @SerialName("vip") val vip: UserVip? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("nameplate") val nameplate: UserNameplateData? = null,
    @SerialName("user_honour_info") val userHonourInfo: UserHonourInfo? = null,
    @SerialName("is_followed") val isFollowed: Boolean? = null,
    @SerialName("top_photo") val spaceBanner: String? = null,
    @SerialName("theme") val theme: JsonObject? = null, // just empty {}
    @SerialName("sys_notice") val notice: SpaceNotice? = null,
    @SerialName("live_room") val liveRoom: LiveRoomInfo? = null,
    @SerialName("birthday") val birthday: String? = null,
    @SerialName("school") val school: SchoolInfo? = null,
    @SerialName("profession") val profession: UserProfession? = null,
    @SerialName("tags") val tags: List<String> = emptyList(),
    @SerialName("series") val upgradeStatus: UserUpgradeStatus? = null,
    @SerialName("is_senior_member") val isSeniorMember: Boolean? = null,
)

@Serializable
public data class UserFansMedal(
    @SerialName("show") val show: Boolean? = null,
    @SerialName("wear") val wear: Boolean? = null,
    @SerialName("medal") val medal: UserSpaceFanMedal? = null,
)

@Serializable
public data class UserSpaceFanMedal(
    @SerialName("uid") val uid: Int? = null,
    @SerialName("target_id") val targetId: Int? = null,
    @SerialName("medal_id") val medalId: Int? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("medal_name") val name: String? = null,
    @SerialName("medal_color") val color: String? = null,
    @SerialName("intimacy") val intimacy: Int? = null,
    @SerialName("next_intimacy") val nextIntimacy: Int? = null,
    @SerialName("day_limit") val dayLimit: Int? = null,
    @SerialName("today_feed") val todayFeed: Int? = null,
    @SerialName("medal_color_start") val medalColorStart: String? = null,
    @SerialName("medal_color_end") val medalColorEnd: String? = null,
    @SerialName("medal_color_border") val medalColorBorder: String? = null,
    @SerialName("is_lighted") val isLighted: Boolean? = null,
    @SerialName("guard_level") val guardLevel: GuardLevel = GuardLevel.UNKNOWN,
    @SerialName("light_status") val lightStatus: Boolean? = null,
    @SerialName("wearing_status") val isWearing: Boolean? = null,
    @SerialName("score") val score: Int? = null,
)

@Serializable
public data class UserHonourInfo(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("colour") val colour: String? = null,
    @SerialName("tags") val tags: List<String> = emptyList(),
)

@Serializable
public data class SpaceNotice(
    @SerialName("id") val id: Int? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("notice_type") val noticeType: Int? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("text_color") val textColor: String? = null,
    @SerialName("bg_color") val bgColor: String? = null,
)

@Serializable
public data class SchoolInfo(
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class UserProfession(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("show_name") val showName: String? = null,
    @SerialName("is_show") val isShow: Boolean? = null,
    @SerialName("category_one") val categoryOne: String? = null,
    @SerialName("realname") val realName: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("department") val department: String? = null,
)

@Serializable
public data class LiveRoomInfo(
    @SerialName("roomStatus") val hasRoom: Boolean? = null,
    @SerialName("liveStatus") val isLiving: Boolean? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("online") val online: Int? = null,
    @SerialName("roomid") val roomId: Int? = null,
    @SerialName("roundStatus") val isRounding: Int? = null,
    @SerialName("broadcast_type") val broadcastType: Int? = null,
    @SerialName("watched_show") val watchedShow: WatchedShow? = null,
)

@Serializable
public data class UserUpgradeStatus(
    @SerialName("user_upgrade_status") val userUpgradeStatus: Int? = null,
    @SerialName("show_upgrade_window") val showUpgradeWindow: Boolean? = null,
)
