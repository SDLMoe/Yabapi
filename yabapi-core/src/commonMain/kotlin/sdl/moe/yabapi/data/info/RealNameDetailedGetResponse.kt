// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class RealNameDetailedGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: RealNameDetailed,
)

@Serializable
public data class RealNameDetailed(
    @SerialName("status") val status: RealNameStatus = RealNameStatus.UNKNOWN,
    @SerialName("remark") val rejectMsg: String? = null,
    @SerialName("realname") val realname: String? = null,
    @SerialName("card") val card: String? = null,
    @SerialName("card_type") val cardType: IdCardType = IdCardType.UNKNOWN,
)

@Serializable
public enum class RealNameStatus {
    UNKNOWN,

    @SerialName("1")
    VERIFIED,

    @SerialName("3")
    NOT_VERIFIED;
}

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
