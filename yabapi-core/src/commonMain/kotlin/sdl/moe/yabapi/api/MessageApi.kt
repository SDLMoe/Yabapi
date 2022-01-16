package sdl.moe.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.SEND_MESSAGE_URL
import sdl.moe.yabapi.consts.internal.UNREAD_MESSAGE_COUNT_GET_URL
import sdl.moe.yabapi.consts.internal.UNREAD_WHISPER_COUNT_GET_URL
import sdl.moe.yabapi.data.message.MessageContent
import sdl.moe.yabapi.data.message.MessageData
import sdl.moe.yabapi.data.message.MessageSendResponse
import sdl.moe.yabapi.data.message.UnreadMsgCountGetResponse
import sdl.moe.yabapi.data.message.UnreadWhisperCountGetResponse
import sdl.moe.yabapi.data.message.put
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("MessageApi")

/**
 * 获取总共未读消息数量
 */
public suspend fun BiliClient.getUnreadMsgCount(
    context: CoroutineContext = this.context,
): UnreadMsgCountGetResponse = withContext(context) {
    logger.debug { "Getting unread message count..." }
    client.get<UnreadMsgCountGetResponse>(UNREAD_MESSAGE_COUNT_GET_URL).also {
        logger.debug { "Got unread message count: $it" }
    }
}

/**
 * 获取私信未读消息数量
 */
public suspend fun BiliClient.getUnreadWhisperCount(
    context: CoroutineContext = this.context,
): UnreadWhisperCountGetResponse =
    withContext(context) {
        logger.debug { "Getting unread whisper count..." }
        client.get<UnreadWhisperCountGetResponse>(UNREAD_WHISPER_COUNT_GET_URL).also {
            logger.debug { "Got unread whisper count: $it" }
        }
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
    client.post<MessageSendResponse>(SEND_MESSAGE_URL) {
        val params = Parameters.build {
            message.put(this, json)
            putCsrf()
        }
        body = FormDataContent(params)
    }.also {
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
    val loginMid = async { selfMid ?: getBasicInfo().data.mid }
    sendMessage(
        MessageData(
            loginMid.await() ?: error("Not login or network unstable"),
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
