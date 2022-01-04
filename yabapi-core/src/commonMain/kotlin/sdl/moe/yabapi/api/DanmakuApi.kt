// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.protoBuf
import sdl.moe.yabapi.consts.video.VIDEO_DANMAKU_WEB_URL
import sdl.moe.yabapi.data.danmaku.DanmakuResponse
import sdl.moe.yabapi.enums.danmaku.DanmakuType
import sdl.moe.yabapi.enums.danmaku.DanmakuType.VIDEO
import sdl.moe.yabapi.util.avInt
import sdl.moe.yabapi.util.logger

public object DanmakuApi : BiliApi {
    override val apiName: String = "danmaku"

    init {
        BiliClient.registerApi(this)
    }

    @ExperimentalSerializationApi
    public suspend fun BiliClient.getDanmaku(
        cid: Int,
        part: Int = 1,
        aid: Int? = null,
        type: DanmakuType = VIDEO,
    ): DanmakuResponse = withContext(Platform.ioDispatcher) {
        val showAid = aid?.let { " (av$it)" } ?: ""
        logger.debug { "Getting danmaku for cid $cid$showAid part $part..." }
        val bytes: ByteArray = client.get(VIDEO_DANMAKU_WEB_URL) {
            parameter("type", type.code)
            parameter("oid", cid)
            aid?.let { parameter("pid", aid) }
            parameter("segment_index", part)
        }
        protoBuf.decodeFromByteArray<DanmakuResponse>(bytes).also {
            logger.debug { "Got danmaku for cid $cid$showAid part $part, count: ${it.danmakus.count()}" }
            logger.verbose { "Danmakus: $it" }
        }
    }

    @ExperimentalSerializationApi
    public suspend inline fun BiliClient.getDanmaku(
        bid: String, part: Int = 1, aid: Int? = null, type: DanmakuType = VIDEO,
    ): DanmakuResponse = getDanmaku(bid.avInt, part, aid, type)
}
