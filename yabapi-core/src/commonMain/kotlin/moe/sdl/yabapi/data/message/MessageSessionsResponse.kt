@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.live.LiveRoomStatus
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class MessageSessionsResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: MessageSessions? = null,
) {
    public val list: List<SessionNode>?
        get() = data?.list
}

@Serializable
public data class MessageSessions(
    @SerialName("session_list") val list: List<SessionNode>? = emptyList(),
    @SerialName("has_more") val hasMore: Boolean? = null,
    @SerialName("anti_disturb_cleaning") val antiDisturbCleaning: Boolean? = null,
    @SerialName("is_address_list_empty") val isAddressListEmpty: Boolean? = null,
    @SerialName("show_level") val showLevel: Boolean? = null,
)

@Serializable
public data class SessionNode(
    @SerialName("talker_id") val talkerId: Int? = null,
    @SerialName("session_type") val sessionType: Int? = null,
    @SerialName("at_seqno") val atSeq: ULong? = null,
    @SerialName("top_ts") val topTs: ULong? = null,
    @SerialName("group_name") val groupName: String? = null,
    @SerialName("group_cover") val groupCover: String? = null,
    @SerialName("is_follow") val isFollow: Boolean? = null,
    @SerialName("is_dnd") val isDnd: Boolean? = null,
    @SerialName("ack_seqno") val ackSeq: ULong? = null,
    @SerialName("ack_ts") val ackTimestamp: ULong? = null,
    @SerialName("session_ts") val sessionTimestamp: ULong? = null,
    @SerialName("unread_count") val unreadCount: Int? = null,
    @SerialName("last_msg") val lastMsg: RecvMessage? = null,
    @SerialName("group_type") val groupType: Int? = null,
    @SerialName("can_fold") val canFold: Boolean? = null,
    @SerialName("status") val status: Int? = null,
    @SerialName("max_seqno") val maxSeq: ULong? = null,
    @SerialName("new_push_msg") val newPushMsg: Int? = null,
    @SerialName("setting") val setting: Int? = null,
    @SerialName("is_guardian") val isGuardian: Boolean? = null,
    @SerialName("is_intercept") val isIntercept: Boolean? = null,
    @SerialName("is_trust") val isTrust: Boolean? = null,
    @SerialName("system_msg_type") val systemMsgType: Int? = null,
    @SerialName("live_status") val liveStatus: LiveRoomStatus? = null,
    @SerialName("biz_msg_unread_count") val bizMsgUnreadCount: Int? = null,
)
