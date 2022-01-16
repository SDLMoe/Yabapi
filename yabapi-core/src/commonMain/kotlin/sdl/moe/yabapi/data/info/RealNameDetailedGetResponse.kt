package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.info.IdCardType.CN_PASSPORT
import sdl.moe.yabapi.data.info.IdCardType.CN_PERMANENT_RESIDENCY
import sdl.moe.yabapi.data.info.IdCardType.HK_OR_MACAU
import sdl.moe.yabapi.data.info.IdCardType.ID_CARD
import sdl.moe.yabapi.data.info.IdCardType.OTHER
import sdl.moe.yabapi.data.info.IdCardType.TW
import sdl.moe.yabapi.data.info.RealNameStatus.NOT_VERIFIED
import sdl.moe.yabapi.data.info.RealNameStatus.VERIFIED

/**
 * @param code [GeneralCode]
 * @param message 错误信息
 * @param data [RealNameDetailed]
 */
@Serializable
public data class RealNameDetailedGetResponse(
    @SerialName("code") val code: GeneralCode = GeneralCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RealNameDetailed? = null,
)

/**
 * @param status 状态 [RealNameStatus]
 * @param remark 备注
 * @param realname 遮蔽真名, 仅 *
 * @param card 证件号码 仅保留首尾两位
 * @param cardType 证件类型 [IdCardType]
 */
@Serializable
public data class RealNameDetailed(
    @SerialName("status") val status: RealNameStatus = RealNameStatus.UNKNOWN,
    @SerialName("remark") val remark: String? = null,
    @SerialName("realname") val realname: String? = null,
    @SerialName("card") val card: String? = null,
    @SerialName("card_type") val cardType: IdCardType = IdCardType.UNKNOWN,
)

/**
 * 实名状态
 *
 * @property [UNKNOWN] 未知
 * @property [VERIFIED] 认证
 * @property [NOT_VERIFIED] 未认证
 */
@Serializable
public enum class RealNameStatus {
    UNKNOWN,

    @SerialName("1")
    VERIFIED,

    @SerialName("3")
    NOT_VERIFIED;
}

/**
 * 证件类型
 *
 * @property [UNKNOWN] 未知
 * @property [ID_CARD] 大陆身份证
 * @property [HK_OR_MACAU] 港澳居民来往内地通行证
 * @property [TW] 台湾居民来往大陆通行证
 * @property [CN_PASSPORT] 护照(中国签发)
 * @property [CN_PERMANENT_RESIDENCY] 外国人永久居留证
 * @property [OTHER] 其他
 */
@Serializable
public enum class IdCardType {
    UNKNOWN,

    @SerialName("0")
    ID_CARD,

    @SerialName("2")
    HK_OR_MACAU,

    @SerialName("3")
    TW,

    @SerialName("4")
    CN_PASSPORT,

    @SerialName("5")
    CN_PERMANENT_RESIDENCY,

    @SerialName("6")
    OTHER;
}
