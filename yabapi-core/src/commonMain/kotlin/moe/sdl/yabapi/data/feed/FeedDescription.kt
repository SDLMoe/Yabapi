@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.data.info.OfficialRole
import moe.sdl.yabapi.data.info.VipLabel
import moe.sdl.yabapi.data.info.VipType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class FeedDescription(
    @SerialName("uid") val uid: Long? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("rid") val rid: ULong? = null,
    @SerialName("acl") val acl: Int? = null,
    @SerialName("view") val view: Int? = null,
    @SerialName("repost") val repost: Int? = null,
    @SerialName("comment") val comment: Int? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("is_liked") val isLiked: Boolean? = null,
    @SerialName("dynamic_id") val dynamicId: ULong? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("pre_dy_id") val preFeedId: ULong? = null,
    @SerialName("orig_dy_id") val originFeedId: ULong? = null,
    @SerialName("orig_type") val originType: Int? = null,
    @SerialName("user_profile") val userProfile: FeedUserProfile? = null,
    @SerialName("uid_type") val uidType: Int? = null,
    @SerialName("stype") val sType: Int? = null,
    @SerialName("r_type") val rType: Int? = null,
    @SerialName("inner_id") val innerId: Long? = null,
    @SerialName("status") val status: Int? = null,
    @SerialName("dynamic_id_str") val feedIdStr: String? = null,
    @SerialName("pre_dy_id_str") val preFeedIdStr: String? = null,
    @SerialName("orig_dy_id_str") val originFeedIdStr: String? = null,
    @SerialName("rid_str") val ridStr: String? = null,
    @SerialName("bvid") val bvId: String? = null,
    @SerialName("origin") val origin: FeedDescription? = null,
    @SerialName("previous") val previous: FeedDescription? = null,
)

@Serializable
public data class UserInfo(
    @SerialName("uid") val uid: Long? = null,
    @SerialName("uname") val uname: String? = null,
    @SerialName("face") val face: String? = null,
    @SerialName("face_nft") val avatarNft: Boolean? = null,
)

@Serializable
public data class FeedUserCard(
    @SerialName("official_verify") val officialCertify: OfficialCertify? = null,
)

@Serializable
public data class FeedVip(
    @SerialName("vipType") val vipType: VipType = VipType.UNKNOWN,
    @SerialName("vipDueDate") val vipDueDate: Long? = null,
    @SerialName("vipStatus") val vipStatus: Long? = null,
    @SerialName("themeType") val themeType: Int? = null,
    @SerialName("label") val label: VipLabel? = null,
    @SerialName("avatar_subscript") val avatarSubscript: Int? = null,
    @SerialName("nickname_color") val nicknameColor: String? = null,
    @SerialName("role") val role: OfficialRole = OfficialRole.UNKNOWN,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String? = null,
)
