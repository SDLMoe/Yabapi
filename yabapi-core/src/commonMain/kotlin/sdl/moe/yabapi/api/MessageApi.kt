// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
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
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("MessageApi")

public suspend fun BiliClient.getUnreadMsgCount(): UnreadMsgCountGetResponse = withContext(Platform.ioDispatcher) {
    logger.debug { "Getting unread message count..." }
    client.get<UnreadMsgCountGetResponse>(UNREAD_MESSAGE_COUNT_GET_URL).also {
        logger.debug { "Got unread message count: $it" }
    }
}

public suspend fun BiliClient.getUnreadWhisperCount(): UnreadWhisperCountGetResponse =
    withContext(Platform.ioDispatcher) {
        logger.debug { "Getting unread whisper count..." }
        client.get<UnreadWhisperCountGetResponse>(UNREAD_WHISPER_COUNT_GET_URL).also {
            logger.debug { "Got unread whisper count: $it" }
        }
    }

public suspend fun BiliClient.sendMessage(message: MessageData): MessageSendResponse =
    withContext(Platform.ioDispatcher) {
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

public suspend fun BiliClient.sendMessageTo(targetMid: Int, messageContent: MessageContent): MessageSendResponse =
    withContext(Platform.ioDispatcher) {
        val loginMid = async { getBasicInfo().data.mid }
        sendMessage(MessageData(
            loginMid.await() ?: error("Not login or network unstable"),
            targetMid,
            messageContent
        ))
    }
