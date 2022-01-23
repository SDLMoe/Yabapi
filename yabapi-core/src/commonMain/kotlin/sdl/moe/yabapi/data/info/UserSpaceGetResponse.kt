@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.data.live.GuardLevel
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class UserSpaceGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: UserSpace,
)

@Serializable
public data class UserSpace(
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("sex") val sex: String,
    @SerialName("face") val avatar: String,
    @SerialName("face_nft") val faceNft: String,
    @SerialName("sign") val bio: String,
    @SerialName("rank") val rank: Int,
    @SerialName("level") val level: Int,
    @SerialName("jointime") val joinTime: Long,
    @SerialName("moral") val moral: Int,
    @SerialName("silence") val isBanned: Boolean,
    @SerialName("coins") val coins: Int,
    @SerialName("fans_badge") val hasFansBadge: Boolean,
    @SerialName("fans_medal") val fansMedal: UserFansMedal,
    @SerialName("official") val official: Official,
    @SerialName("vip") val vip: Vip,
    @SerialName("pendant") val pendant: Pendant,
    @SerialName("nameplate") val nameplate: UserNameplateData,
    @SerialName("user_honour_info") val userHonourInfo: UserHonourInfo,
    @SerialName("is_followed") val isFollowed: Boolean,
    @SerialName("top_photo") val spaceBanner: String,
    @SerialName("theme") val theme: JsonObject, // just empty {}
    @SerialName("sys_notice") val notice: SpaceNotice,
    @SerialName("live_room") val liveRoom: LiveRoomInfo,
    @SerialName("birthday") val birthday: String,
    @SerialName("school") val school: SchoolInfo? = null,
    @SerialName("profession") val profession: UserProfession,
    @SerialName("tags") val tags: List<String> = emptyList(),
    @SerialName("series") val upgradeStatus: UserUpgradeStatus,
    @SerialName("is_senior_member") val isSeniorMember: Boolean,
)

@Serializable
public data class UserFansMedal(
    @SerialName("show") val show: Boolean,
    @SerialName("wear") val wear: Boolean,
    @SerialName("medal") val medal: UserSpaceFanMedal? = null,
)

@Serializable
public data class UserSpaceFanMedal(
    @SerialName("uid") val uid: Int,
    @SerialName("target_id") val targetId: Int,
    @SerialName("medal_id") val medalId: Int,
    @SerialName("level") val level: Int,
    @SerialName("medal_name") val name: String,
    @SerialName("medal_color") val color: String,
    @SerialName("intimacy") val intimacy: Int,
    @SerialName("next_intimacy") val nextIntimacy: Int,
    @SerialName("day_limit") val dayLimit: Int,
    @SerialName("today_feed") val todayFeed: Int? = null,
    @SerialName("medal_color_start") val medalColorStart: String,
    @SerialName("medal_color_end") val medalColorEnd: String,
    @SerialName("medal_color_border") val medalColorBorder: String,
    @SerialName("is_lighted") val isLighted: Boolean,
    @SerialName("guard_level") val guardLevel: GuardLevel = GuardLevel.UNKNOWN,
    @SerialName("light_status") val lightStatus: Boolean,
    @SerialName("wearing_status") val isWearing: Boolean,
    @SerialName("score") val score: Int,
)

@Serializable
public data class UserHonourInfo(
    @SerialName("mid") val mid: Int,
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
)

@Serializable
public data class LiveRoomInfo(
    @SerialName("roomStatus") val hasRoom: Boolean,
    @SerialName("liveStatus") val isLiving: Boolean,
    @SerialName("url") val url: String,
    @SerialName("title") val title: String,
    @SerialName("cover") val cover: String,
    @SerialName("online") val online: Int,
    @SerialName("roomid") val roomId: Int,
    @SerialName("roundStatus") val isRounding: Int,
    @SerialName("broadcast_type") val broadcastType: Int,
)

@Serializable
public data class UserUpgradeStatus(
    @SerialName("user_upgrade_status") val userUpgradeStatus: Int,
    @SerialName("show_upgrade_window") val showUpgradeWindow: Boolean,
)
