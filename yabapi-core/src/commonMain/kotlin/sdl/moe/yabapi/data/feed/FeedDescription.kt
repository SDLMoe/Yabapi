package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.info.OfficialCertify
import sdl.moe.yabapi.data.info.OfficialRole
import sdl.moe.yabapi.data.info.VipLabel
import sdl.moe.yabapi.data.info.VipType

@Serializable
public data class FeedDescription(
    @SerialName("uid") val uid: Int,
    @SerialName("type") val type: Int,
    @SerialName("rid") val rid: Int,
    @SerialName("acl") val acl: Int? = null,
    @SerialName("view") val view: Int,
    @SerialName("repost") val repost: Int,
    @SerialName("comment") val comment: Int? = null,
    @SerialName("like") val like: Int,
    @SerialName("is_liked") val isLiked: Boolean,
    @SerialName("dynamic_id") val dynamic_id: ULong,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("pre_dy_id") val preFeedId: ULong? = null,
    @SerialName("orig_dy_id") val originFeedId: ULong? = null,
    @SerialName("orig_type") val originType: Int,
    @SerialName("user_profile") val userProfile: FeedUserProfile,
    @SerialName("uid_type") val uidType: Int,
    @SerialName("stype") val sType: Int? = null,
    @SerialName("r_type") val rType: Int,
    @SerialName("inner_id") val innerId: Int? = null,
    @SerialName("status") val status: Int,
    @SerialName("dynamic_id_str") val feedIdStr: String,
    @SerialName("pre_dy_id_str") val preFeedIdStr: String,
    @SerialName("orig_dy_id_str") val originFeedIdStr: String,
    @SerialName("rid_str") val ridStr: String,
    @SerialName("bvid") val bvId: String,
    @SerialName("origin") val origin: FeedDescription? = null,
)

@Serializable
public data class UserInfo(
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val uname: String,
    @SerialName("face") val face: String,
    @SerialName("face_nft") val faceNft: Boolean,
)

@Serializable
public data class FeedUserCard(
    @SerialName("official_verify") val officialCertify: OfficialCertify,
)

@Serializable
public data class FeedVip(
    @SerialName("vipType") val vipType: VipType = VipType.UNKNOWN,
    @SerialName("vipDueDate") val vipDueDate: Long,
    @SerialName("vipStatus") val vipStatus: Long,
    @SerialName("themeType") val themeType: Int,
    @SerialName("label") val label: VipLabel,
    @SerialName("avatar_subscript") val avatarSubscript: Int,
    @SerialName("nickname_color") val nicknameColor: String,
    @SerialName("role") val role: OfficialRole = OfficialRole.UNKNOWN,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String,
)
