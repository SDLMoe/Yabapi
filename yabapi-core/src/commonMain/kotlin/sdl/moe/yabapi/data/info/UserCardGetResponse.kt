@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 * 用戶卡片獲取返回
 * @param code [GeneralCode]
 * @param data [UserCardGetData]
 * @property card 封裝, 直接訪問 UserCard
 */
@Serializable
public data class UserCardGetResponse(
    @SerialName("code") val code: GeneralCode,
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
    @SerialName("archive_count") val archiveCount: Int,
    @SerialName("article_count") val articleCount: Int,
    @SerialName("follower") val follower: Int,
    @SerialName("like_num") val like: Int,
)

/**
 * @param space 空間頭圖數據, 僅在要求時返回
 */
@Serializable
public data class UserCard(
    @SerialName("mid") val mid: Int,
    @SerialName("approve") val approve: Boolean,
    @SerialName("name") val name: String,
    @SerialName("sex") val sex: String,
    @SerialName("rank") val rank: Int,
    @SerialName("face") val avatar: String,
    @SerialName("face_nft") val faceNft: String,
    @SerialName("DisplayRank") val displayRank: String,
    @SerialName("regtime") val regtime: Long,
    @SerialName("spacesta") val spacesta: Int,
    @SerialName("birthday") val birthday: String,
    @SerialName("place") val place: String,
    @SerialName("description") val description: String,
    @SerialName("article") val article: Int,
    @SerialName("attentions") val attentions: List<String>,
    @SerialName("fans") val fans: Int,
    @SerialName("friend") val friend: Int,
    @SerialName("attention") val attention: Int,
    @SerialName("sign") val bio: String,
    @SerialName("level_info") val levelInfo: LevelInfo,
    @SerialName("pendant") val pendant: Pendant,
    @SerialName("nameplate") val nameplate: UserNameplateData,
    @SerialName("Official") val official: Official,
    @SerialName("official_verify") val officialVerify: OfficialCertify,
    @SerialName("vip") val vip: UserCardVip,
    @SerialName("space") val space: SpaceBanner? = null,
)

/**
 * @param type vip類型 無/月度/年度
 */
@Serializable
public data class UserCardVip(
    @SerialName("type") val type: VipType = VipType.UNKNOWN,
    @SerialName("status") val hasVip: Boolean,
    @SerialName("due_date") val dueDate: Long,
    @SerialName("vip_pay_type") val isPaid: Boolean? = null,
    @SerialName("theme_type") val themeType: Int,
    @SerialName("label") val label: VipLabel,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean,
    @SerialName("nickname_color") val nicknameColor: String,
    @SerialName("role") val role: Int,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String,
    @SerialName("vipType") val vipType: VipType = VipType.UNKNOWN,
    @SerialName("vipStatus") val vipStatus: Boolean? = null,
)

/**
 * @param small 小圖
 * @param large 大圖
 * @see [sdl.moe.yabapi.util.buildImageUrl]
 */
@Serializable
public data class SpaceBanner(
    @SerialName("s_img") val small: String,
    @SerialName("l_img") val large: String,
)
