@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.feed.UserFanInfo
import moe.sdl.yabapi.data.info.LevelInfo
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.data.info.Pendant
import moe.sdl.yabapi.data.info.UserNameplateData
import moe.sdl.yabapi.data.info.UserSpaceFanMedal
import moe.sdl.yabapi.data.relation.RelationVipInfo
import moe.sdl.yabapi.data.sticker.StickerSize
import moe.sdl.yabapi.data.sticker.StickerType
import moe.sdl.yabapi.data.sticker.StickerType.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class Comment(
    @SerialName("rpid") val replyId: ULong? = null,
    @SerialName("oid") val oid: ULong? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("root") val root: ULong? = null,
    @SerialName("parent") val parent: ULong? = null,
    @SerialName("dialog") val dialog: ULong? = null,
    @SerialName("count") val count: Int? = null,
    @SerialName("rcount") val rcount: Int? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("fansgrade") val fanLevel: Int? = null,
    @SerialName("attr") val attr: Int? = null,
    @SerialName("ctime") val ctime: Long? = null,
    @SerialName("rpid_str") val rpidStr: String? = null,
    @SerialName("root_str") val rootStr: String? = null,
    @SerialName("parent_str") val parentStr: String? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("action") val action: Int? = null,
    @SerialName("member") val member: CommentMember? = null,
    @SerialName("content") val content: Content? = null,
    @SerialName("replies") val replies: List<Comment> = emptyList(),
    @SerialName("assist") val assist: Int? = null,
    @SerialName("folder") val folder: CommentFolder? = null,
    @SerialName("up_action") val upAction: UpAction? = null,
    @SerialName("show_follow") val showFollow: Boolean? = null,
    @SerialName("invisible") val invisible: Boolean? = null,
    @SerialName("reply_control") val replyControl: ReplyControl? = null,
) {
    @Serializable
    public data class Content(
        @SerialName("message") val message: String? = null,
        @SerialName("plat") val plat: Int? = null,
        @SerialName("device") val device: String? = null,
        @SerialName("members") val members: List<CommentMember>? = null,
        @SerialName("jump_url") val jumpUrl: Map<String, JumpUrl> = mapOf(),
        @SerialName("max_line") val maxLine: Int? = null,
        @SerialName("ats") val ats: List<Int>? = null,
        @SerialName("emote") val stickers: Map<String, Sticker> = emptyMap(),
    )

    @Serializable
    public data class JumpUrl(
        @SerialName("title") val title: String? = null,
        @SerialName("state") val state: Int? = null,
        @SerialName("prefix_icon") val prefixIcon: String? = null,
        @SerialName("app_url_schema") val appUrlSchema: String? = null,
        @SerialName("app_name") val appName: String? = null,
        @SerialName("app_package_name") val appPackageName: String? = null,
        @SerialName("click_report") val clickReport: String? = null,
        @SerialName("is_half_screen") val isHalfScreen: Boolean? = null,
        @SerialName("exposure_report") val exposureReport: String? = null,
        @SerialName("underline") val underline: Boolean? = null,
        @SerialName("match_once") val matchOnce: Boolean? = null,
        @SerialName("pc_url") val pcUrl: String? = null,
    )

    @Serializable
    public data class Sticker(
        @SerialName("id") val id: ULong? = null,
        @SerialName("package_id") val packageId: ULong? = null,
        @SerialName("state") val state: Int? = null,
        @SerialName("type") val type: StickerType = UNKNOWN,
        @SerialName("attr") val attr: Int? = null,
        @SerialName("text") val text: String? = null,
        @SerialName("url") val url: String? = null,
        @SerialName("gif_url") val gifUrl: String? = null,
        @SerialName("meta") val meta: Meta? = null,
        @SerialName("mtime") val mtime: ULong? = null,
        @SerialName("jump_title") val jumpTitle: String? = null,
    ) {
        @Serializable
        public data class Meta(
            @SerialName("size") val size: StickerSize? = null,
            @SerialName("alias") val alias: String? = null,
        )
    }

    @Serializable
    public data class UpAction(
        @SerialName("like") val like: Boolean? = null,
        @SerialName("reply") val reply: Boolean? = null,
    )

    @Serializable
    public data class ReplyControl(
        @SerialName("sub_reply_entry_text") val subReplyEntryText: String? = null,
        @SerialName("sub_reply_title_text") val subReplyTitleText: String? = null,
        @SerialName("time_desc") val timeDesc: String? = null,
    )
}

/**
 * @param mid 用户 mid
 */
@Serializable
public data class CommentMember(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("avatar") val avatar: String? = null,
    @SerialName("rank") val rank: String? = null,
    @SerialName("DisplayRank") val displayRank: String? = null,
    @SerialName("face_nft_new") val faceNftNew: Boolean? = null,
    @SerialName("is_senior_member") val isSeniorMember: Boolean? = null,
    @SerialName("level_info") val levelInfo: LevelInfo? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("nameplate") val nameplate: UserNameplateData? = null,
    @SerialName("official_verify") val officialCertify: OfficialCertify? = null,
    @SerialName("vip") val vip: RelationVipInfo? = null,
    @SerialName("fans_detail") val fansDetail: UserSpaceFanMedal? = null,
    @SerialName("following") val following: Boolean? = null,
    @SerialName("is_followed") val isFollowed: Boolean? = null,
    @SerialName("user_sailing") val userSailing: UserSailing? = null,
    @SerialName("is_contractor") val isContractor: Boolean? = null,
    @SerialName("contract_desc") val contractDesc: String? = null,
)

@Serializable
public data class UserSailing(
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("cardbg") val cardBag: CardBag? = null,
    @SerialName("cardbg_with_focus") val cardBagWithFocus: CardBag? = null,
) {
    @Serializable
    public data class Pendant(
        @SerialName("id") val id: Int? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("image") val image: String? = null,
        @SerialName("jump_url") val jumpUrl: String? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("image_enhance") val imageEnhance: String? = null,
        @SerialName("image_enhance_frame") val imageEnhanceFrame: String? = null,
    )
}

@Serializable
public data class CardBag(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("jump_url") val jumpUrl: String? = null,
    @SerialName("fan") val fan: UserFanInfo? = null,
    @SerialName("type") val type: String? = null,
)
