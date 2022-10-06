@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class MySpaceGetResponse(
    @SerialName("code") val code: GeneralCode = GeneralCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: MySpace? = null,
)

@Serializable
public data class MySpace(
    @SerialName("mid") val mid: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("rank") val rank: Int? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("jointime") val joinTime: Long? = null,
    @SerialName("moral") val moral: Int? = null,
    @SerialName("silence") val isBanned: Boolean? = null,
    @SerialName("email_status") val hasEmailBind: Boolean? = null,
    @SerialName("tel_status") val hasTelBind: Boolean? = null,
    @SerialName("identification") val identification: Int? = null,
    @SerialName("vip") val vip: UserVip? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("nameplate") val nameplate: UserNameplateData? = null,
    @SerialName("official") val official: Official? = null,
    @SerialName("birthday") val birthday: Long? = null,
    @SerialName("is_tourist") val isTourist: Boolean? = null,
    @SerialName("is_fake_account") val isFakeAccount: Boolean? = null,
    @SerialName("pin_prompting") val pinPrompting: Boolean? = null,
    @SerialName("is_deleted") val isDeleted: Boolean? = null,
    @SerialName("in_reg_audit") val isRegAudit: Boolean? = null,
    @SerialName("is_rip_user") val isRipUser: Boolean? = null,
    @SerialName("profession") val profession: UserProfession? = null,
    @SerialName("face_nft") val avatarNft: String? = null,
    @SerialName("face_nft_new") val avatarNftNew: String? = null,
    @SerialName("is_senior_member") val isSeniorMember: Boolean? = null,
    @SerialName("level_exp") val levelExp: LevelInfo? = null,
    @SerialName("coins") val coins: Double? = null,
    @SerialName("following") val following: Int? = null,
    @SerialName("follower") val follower: Int? = null,
)
