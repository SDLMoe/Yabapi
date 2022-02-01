@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.data.info.UserVip
import moe.sdl.yabapi.data.info.VipLabel
import moe.sdl.yabapi.data.info.VipStatus
import moe.sdl.yabapi.data.info.VipType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 關係 Node
 * @param mid mid
 * @param attribute 當前賬戶對該名用戶的關注狀態
 * @param mtime 關注時間
 * @param tag 不明
 * @param special 是否特別關注
 * @param contractInfo 騎士相關
 * @param name 用戶名
 * @param avatar 頭像
 * @param bio 個人簡介/簽名
 * @param officialCertify 官方狀態
 * @param vip [RelationVipInfo]
 */
@Serializable
public data class RelationUserNode(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("attribute") val attribute: RelationAttribute = RelationAttribute.UNKNOWN,
    @SerialName("mtime") val mtime: Int? = null,
    @SerialName("tag") val tag: List<Int> = emptyList(),
    @SerialName("special") val special: Boolean? = null,
    @SerialName("contract_info") val contractInfo: ContractInfo? = null,
    @SerialName("uname") val name: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("official_verify") val officialCertify: OfficialCertify? = null,
    @SerialName("vip") val vip: RelationVipInfo? = null,
)

/**
 * @see [UserVip]
 */
@Serializable
public data class RelationVipInfo(
    @SerialName("vipType") val type: VipType = VipType.UNKNOWN,
    @SerialName("vipDueDate") val dueDate: Long? = null,
    @SerialName("dueRemark") val dueRemark: String? = null,
    @SerialName("accessStatus") val accessStatus: Int? = null,
    @SerialName("vipStatus") val status: VipStatus = VipStatus.UNKNOWN,
    @SerialName("vipStatusWarn") val statusWarn: String? = null,
    @SerialName("themeType") val themeType: Int? = null,
    @SerialName("label") val label: VipLabel? = null,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean? = null,
    @SerialName("nickname_color") val nicknameColor: String? = null,
    // @SerialName("role") val role: Int? = null,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String? = null,
)

/**
 * 推測是*騎士*相關功能, 具體意義未知
 */
@Serializable
public data class ContractInfo(
    @SerialName("is_contractor") val isContractor: Boolean? = null,
    @SerialName("ts") val timestamp: Long? = null,
    @SerialName("is_contract") val isContract: Boolean? = null,
    @SerialName("user_attr") val userAttribute: Int? = null,
)
