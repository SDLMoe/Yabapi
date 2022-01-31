@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 用戶卡片獲取返回
 * @param code [GeneralCode]
 * @param data [UserCardGetData]
 * @property card 封裝, 直接訪問 UserCard
 */
@Serializable
public data class UserCardGetResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: UserCardGetData? = null,
) {
    /**
     * 封裝, 減少一層
     */
    inline val card: UserCard?
        get() = data?.card
}

@Serializable
public data class UserCardGetData(
    @SerialName("card") val card: UserCard? = null,
    @SerialName("space") val space: SpaceBanner? = null,
    @SerialName("following") val isFollowing: Boolean? = null,
    @SerialName("archive_count") val archiveCount: Int? = null,
    @SerialName("article_count") val articleCount: Int? = null,
    @SerialName("follower") val follower: Int? = null,
    @SerialName("like_num") val like: Int? = null,
)

/**
 * @param space 空間頭圖數據, 僅在要求時返回
 */
@Serializable
public data class UserCard(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("approve") val approve: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("rank") val rank: Int? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("face_nft") val avatarNft: String? = null,
    @SerialName("DisplayRank") val displayRank: String? = null,
    @SerialName("regtime") val registerTime: Long? = null,
    @SerialName("spacesta") val spacesta: Int? = null,
    @SerialName("birthday") val birthday: String? = null,
    @SerialName("place") val place: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("article") val article: Int? = null,
    @SerialName("attentions") val attentions: List<String>,
    @SerialName("fans") val fans: Int? = null,
    @SerialName("friend") val friend: Int? = null,
    @SerialName("attention") val attention: Int? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("level_info") val levelInfo: LevelInfo? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("nameplate") val nameplate: UserNameplateData? = null,
    @SerialName("Official") val official: Official? = null,
    @SerialName("official_verify") val officialVerify: OfficialCertify? = null,
    @SerialName("vip") val vip: UserCardVip? = null,
    @SerialName("space") val space: SpaceBanner? = null,
    @SerialName("is_senior_member") val isSeniorMember: Boolean? = null,
)

/**
 * @param type vip類型 無/月度/年度
 */
@Serializable
public data class UserCardVip(
    @SerialName("type") val type: VipType = VipType.UNKNOWN,
    @SerialName("status") val hasVip: Boolean? = null,
    @SerialName("due_date") val dueDate: Long? = null,
    @SerialName("vip_pay_type") val isPaid: Boolean? = null,
    @SerialName("theme_type") val themeType: Int? = null,
    @SerialName("label") val label: VipLabel? = null,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean? = null,
    @SerialName("nickname_color") val nicknameColor: String? = null,
    @SerialName("role") val role: Int? = null,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String? = null,
    @SerialName("vipType") val vipType: VipType = VipType.UNKNOWN,
    @SerialName("vipStatus") val vipStatus: Boolean? = null,
)

/**
 * @param small 小圖
 * @param large 大圖
 * @see [moe.sdl.yabapi.util.string.buildImageUrl]
 */
@Serializable
public data class SpaceBanner(
    @SerialName("s_img") val small: String? = null,
    @SerialName("l_img") val large: String? = null,
)
