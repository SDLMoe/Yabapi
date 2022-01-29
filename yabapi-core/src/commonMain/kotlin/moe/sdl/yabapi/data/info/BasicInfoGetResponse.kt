@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * @see BasicInfoData
 */
@Serializable
public data class BasicInfoGetResponse(
    @SerialName("code") val code: GeneralCode = GeneralCode.UNKNOWN,
    @SerialName("message") val message: String,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: BasicInfoData,
)

/**
 * 参数太多只列重要的
 *
 * @param isLogin 是否登录
 * @param mid 用户 mid
 * @param coin 硬币数
 * @param moral 节操值
 * @param username 用户名
 */
@Serializable
public data class BasicInfoData(
    @SerialName("isLogin") val isLogin: Boolean,
    @SerialName("email_verified") val isVerifiedEmail: Boolean? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("face_nft") val avatarNft: Int? = null,
    @SerialName("level_info") val levelInfo: LevelInfo? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("mobile_verified") val isVerifiedMobile: Boolean? = null,
    @SerialName("money") val coin: Double? = null,
    @SerialName("moral") val moral: Double? = null,
    @SerialName("official") val official: Official? = null,
    @SerialName("officialVerify") val officialCertify: OfficialCertify? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("scores") val scores: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("vipDueDate") val vipDueDate: Long? = null,
    @SerialName("vipStatus") val vipStatus: VipStatus = VipStatus.UNKNOWN,
    @SerialName("vipType") val vipType: VipType? = VipType.UNKNOWN,
    @SerialName("vip_pay_type") val vipPayType: Boolean? = null,
    @SerialName("vip_theme_type") val vipThemeType: Int? = null,
    @SerialName("vip_label") val vipLabel: VipLabel? = null,
    @SerialName("vip_avatar_subscript") val isShowSubscript: Boolean? = null,
    @SerialName("vip_nickname_color") val vipNicknameColor: String? = null,
    @SerialName("vip") val vip: UserVip? = null,
    @SerialName("wallet") val wallet: Wallet? = null,
    @SerialName("has_shop") val hasShop: Boolean? = null,
    @SerialName("shop_url") val shopUrl: String? = null,
    @SerialName("allowance_count") val allowanceCount: Int? = null,
    @SerialName("answer_status") val answerStatus: Int? = null,
    @SerialName("is_senior_member") val isSeniorMember: Int? = null,
)
