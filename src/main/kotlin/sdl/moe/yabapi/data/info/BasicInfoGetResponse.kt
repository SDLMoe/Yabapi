// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class BasicInfoGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: BasicInfoData,
)

@Serializable
public data class BasicInfoData(
    @SerialName("isLogin") val isLogin: Boolean,
    @SerialName("email_verified") val isVerifiedEmail: Boolean? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("face_nft") val avatarNft: Int? = null,
    @SerialName("level_info") val levelInfo: JsonObject? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("mobile_verified") val isVerifiedMobile: Boolean? = null,
    @SerialName("money") val coin: Double? = null,
    @SerialName("moral") val moral: Double? = null,
    @SerialName("official") val offcial: JsonObject? = null,
    @SerialName("officialVerify") val offcialVerify: JsonObject? = null,
    @SerialName("pendant") val pendant: JsonObject? = null,
    @SerialName("scores") val scores: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("vipDueDate") val vipDueDate: Long? = null,
    @SerialName("vipStatus") val vipStatus: Boolean? = null,
    @SerialName("vipType") val vipType: Int? = null,
    @SerialName("vip_pay_type") val vipPayType: Boolean? = null,
    @SerialName("vip_theme_type") val vipThemeType: Int? = null,
    @SerialName("vip_label") val vipLabel: JsonObject? = null,
    @SerialName("vip_avatar_subscript") val isShowSubscript: Boolean? = null,
    @SerialName("vip_nickname_color") val vipNicknameColor: String? = null,
    @SerialName("vip") val vip: JsonObject? = null,
    @SerialName("wallet") val wallet: JsonObject? = null,
    @SerialName("has_shop") val hasShop: Boolean? = null,
    @SerialName("shop_url") val shopUrl: String? = null,
    @SerialName("allowance_count") val allowanceCount: Int? = null,
    @SerialName("answer_status") val answerStatus: Int? = null,
)
