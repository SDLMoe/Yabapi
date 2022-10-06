package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.consts.internal.FETCH_MESSAGE_SESSIONS_URL
import moe.sdl.yabapi.consts.internal.FETCH_NEW_MESSAGE_SESSIONS_URL
import moe.sdl.yabapi.consts.internal.FETCH_SESSION_MESSAGES_URL
import moe.sdl.yabapi.consts.internal.MESSAGE_SETTINGS_URL
import moe.sdl.yabapi.consts.internal.SEND_MESSAGE_URL
import moe.sdl.yabapi.consts.internal.UNREAD_MESSAGE_COUNT_GET_URL
import moe.sdl.yabapi.consts.internal.UNREAD_WHISPER_COUNT_GET_URL
import moe.sdl.yabapi.data.message.MessageContent
import moe.sdl.yabapi.data.message.MessageData
import moe.sdl.yabapi.data.message.MessageResponse
import moe.sdl.yabapi.data.message.MessageSendResponse
import moe.sdl.yabapi.data.message.MessageSessionsResponse
import moe.sdl.yabapi.data.message.MessageSettingBuilder
import moe.sdl.yabapi.data.message.ModifyMsgSettingResponse
import moe.sdl.yabapi.data.message.SessionSort
import moe.sdl.yabapi.data.message.SessionType
import moe.sdl.yabapi.data.message.SessionType.NORMAL
import moe.sdl.yabapi.data.message.SessionType.UNKNOWN
import moe.sdl.yabapi.data.message.UnreadMsgCountGetResponse
import moe.sdl.yabapi.data.message.UnreadWhisperCountGetResponse
import moe.sdl.yabapi.data.message.put
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("MessageApi") }

/**
 * 获取总共未读消息数量
 */
public suspend fun BiliClient.getUnreadMsgCount(
    context: CoroutineContext = this.context,
): UnreadMsgCountGetResponse = withContext(context) {
    logger.debug { "Getting unread message count..." }
    client.get(UNREAD_MESSAGE_COUNT_GET_URL)
        .body<String>()
        .deserializeJson<UnreadMsgCountGetResponse>()
        .also { logger.debug { "Got unread message count: $it" } }
}

/**
 * 获取私信未读消息数量
 */
public suspend fun BiliClient.getUnreadWhisperCount(
    context: CoroutineContext = this.context,
): UnreadWhisperCountGetResponse =
    withContext(context) {
        logger.debug { "Getting unread whisper count..." }
        client.get(UNREAD_WHISPER_COUNT_GET_URL)
            .body<String>()
            .deserializeJson<UnreadWhisperCountGetResponse>()
            .also { logger.debug { "Got unread whisper count: $it" } }
    }

/**
 * 发送信息(原始api)
 * @param message 信息属性
 * @see MessageData
 */
public suspend fun BiliClient.sendMessage(
    message: MessageData,
    context: CoroutineContext = this.context,
): MessageSendResponse = withContext(context) {
    logger.debug { "try to send message: $message" }
    client.post(SEND_MESSAGE_URL) {
        val params = Parameters.build {
            message.put(this, Yabapi.defaultJson.value)
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson<MessageSendResponse>().also {
        logger.debug { "Sent message, response: $it" }
    }
}

/**
 * 向目标用户发送消息
 * @param targetMid 目标用户 mid
 * @param messageContent 消息内容
 * @param selfMid 自身 mid, 可选, 若为空则开启一个协程通过 [getBasicInfo] 获取 mid, 获取失败时抛出异常
 * @see MessageData
 */
public suspend fun BiliClient.sendMessageTo(
    targetMid: Long,
    messageContent: MessageContent,
    selfMid: Long? = null,
    context: CoroutineContext = this.context,
): MessageSendResponse = withContext(context) {
    val loginMid = async {
        selfMid ?: getBasicInfo().data.mid ?: error("failed to get current mid, may not login or network unstable")
    }
    sendMessage(
        MessageData(
            loginMid.await(),
            targetMid,
            messageContent
        )
    )
}

/**
 * 向目标用户发送文本消息
 * @see sendMessageTo
 */
public suspend inline fun BiliClient.sendTextMsgTo(
    targetMid: Long,
    text: String,
    selfMid: Long? = null,
    context: CoroutineContext = this.context,
): MessageSendResponse = sendMessageTo(targetMid, MessageContent.Text(text), selfMid, context)

public suspend fun BiliClient.modifyMessageSetting(
    app: String = "web",
    build: Int = 0,
    context: CoroutineContext = this.context,
    builder: MessageSettingBuilder.() -> Unit,
): ModifyMsgSettingResponse = withContext(context) {
    logger.debug { "Try to modify message setting..." }
    val list = MessageSettingBuilder().apply(builder).build()
    client.post(MESSAGE_SETTINGS_URL) {
        setBody(
            FormDataContent(
                Parameters.build {
                    putCsrf("csrf")
                    append("build", build.toString())
                    append("mobi_app", app)
                    list.forEach { (k, v) -> append(k, v.toString()) }
                }
            )
        )
    }.body<String>().deserializeJson<ModifyMsgSettingResponse>().also {
        logger.debug { "modify message setting resp: $it..." }
    }
}

public suspend fun BiliClient.fetchMessageSessions(
    type: SessionType = NORMAL,
    sort: SessionSort = SessionSort.TIME,
    endTimestamp: Long? = null,
    foldGroup: Boolean = true,
    foldUnfollowed: Boolean = false,
    build: Int = 0,
    app: String = "web",
    context: CoroutineContext = this.context,
): MessageSessionsResponse = withContext(context) {
    logger.debug { "Try to fetch MessageSessionsResponse, Type $type Sort $sort Timestamp $endTimestamp" }
    require(type != UNKNOWN) { "Do not allow $type session type" }
    client.get(FETCH_MESSAGE_SESSIONS_URL) {
        endTimestamp?.also { parameter("end_ts", it) }
        parameter("session_type", type.code)
        parameter("sort_rule", sort.code)
        parameter("group_fold", if (foldGroup) "1" else "0")
        parameter("unfollow_fold", if (foldUnfollowed) "1" else "0")
        parameter("build", build)
        parameter("mobi_app", app)
    }.body<String>().deserializeJson<MessageSessionsResponse>().also {
        logger.debug { "Recv MessageSessionsResponse: $it" }
    }
}

/**
 * @param beginTimestamp unit: microsecond
 */
public suspend fun BiliClient.fetchNewMessageSessions(
    beginTimestamp: Long,
    build: Int = 0,
    app: String = "web",
    context: CoroutineContext = this.context,
): MessageSessionsResponse = withContext(context) {
    logger.debug { "Getting new sessions after $beginTimestamp..." }
    client.get(FETCH_NEW_MESSAGE_SESSIONS_URL) {
        parameter("begin_ts", beginTimestamp)
        parameter("build", build)
        parameter("mobi_app", app)
    }.body<String>().deserializeJson<MessageSessionsResponse>().also {
        logger.debug { "Got new sessions after $beginTimestamp: $it" }
    }
}

public suspend inline fun BiliClient.fetchNewMessageSessions(
    begin: Instant,
    build: Int = 0,
    app: String = "web",
    context: CoroutineContext = this.context,
): MessageSessionsResponse = fetchNewMessageSessions(begin.toEpochMilliseconds() * 1_000, build, app, context)

public suspend fun BiliClient.fetchSessionMessage(
    talkerId: Long,
    beginSeq: ULong? = null,
    sessionType: SessionType = NORMAL,
    senderDeviceId: Int = 1,
    size: Int = 50,
    build: Int = 0,
    app: String = "web",
    context: CoroutineContext = this.context,
): MessageResponse = withContext(context) {
    logger.debug { "fetch session message for $talkerId" }
    client.get(FETCH_SESSION_MESSAGES_URL) {
        parameter("sender_device_id", senderDeviceId)
        parameter("talker_id", talkerId)
        parameter("session_type", sessionType.code)
        beginSeq?.also { parameter("begin_seqno", beginSeq) }
        parameter("size", size)
        parameter("build", build)
        parameter("mobi_app", app)
    }.body<String>().deserializeJson<MessageResponse>().also {
        logger.debug { "fetched session message  for $talkerId: $it" }
    }
}
