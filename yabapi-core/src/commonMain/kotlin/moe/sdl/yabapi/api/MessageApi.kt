package moe.sdl.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.consts.internal.SEND_MESSAGE_URL
import moe.sdl.yabapi.consts.internal.UNREAD_MESSAGE_COUNT_GET_URL
import moe.sdl.yabapi.consts.internal.UNREAD_WHISPER_COUNT_GET_URL
import moe.sdl.yabapi.data.message.MessageContent
import moe.sdl.yabapi.data.message.MessageData
import moe.sdl.yabapi.data.message.MessageSendResponse
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
    client.get<String>(UNREAD_MESSAGE_COUNT_GET_URL)
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
        client.get<String>(UNREAD_WHISPER_COUNT_GET_URL)
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
    client.post<String>(SEND_MESSAGE_URL) {
        val params = Parameters.build {
            message.put(this, Yabapi.defaultJson.value)
            putCsrf()
        }
        body = FormDataContent(params)
    }.deserializeJson<MessageSendResponse>().also {
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
    targetMid: Int,
    messageContent: MessageContent,
    selfMid: Int? = null,
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
    targetMid: Int,
    text: String,
    selfMid: Int? = null,
    context: CoroutineContext = this.context,
): MessageSendResponse = sendMessageTo(targetMid, MessageContent.Text(text), selfMid, context)
