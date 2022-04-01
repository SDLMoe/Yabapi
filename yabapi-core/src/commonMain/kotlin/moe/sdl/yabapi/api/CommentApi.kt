package moe.sdl.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.COMMENT_GET_URL
import moe.sdl.yabapi.consts.internal.COMMENT_LAZY_GET_URL
import moe.sdl.yabapi.data.comment.CommentReqDsl
import moe.sdl.yabapi.data.comment.CommentGetResponse
import moe.sdl.yabapi.data.comment.CommentSort
import moe.sdl.yabapi.data.comment.CommentType
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.now
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("CommentApi") }

/**
 * @param type 评论区类型 详见 [CommentType]
 * @param id 对应的 ID
 * @param sort 排序方式 详见 [CommentSort]
 * @param page 评论页码选择 从 0 开始
 * @param count 每页数量 可用 1..30
 */
private suspend fun BiliClient.getComment(
    type: CommentType,
    id: ULong,
    lazy: Boolean,
    page: Int,
    count: Int,
    sort: CommentSort,
    context: CoroutineContext,
): CommentGetResponse = withContext(context) {
    logger.debug { "Getting root comment for $type $id" }
    client.get<String>(if (lazy) COMMENT_LAZY_GET_URL else COMMENT_GET_URL) {
        parameter("type", type.code)
        parameter("oid", id.toString())
        parameter(if (lazy) "next" else "pn", page)
        parameter("ps", count)
        parameter("mode", sort.code)
        parameter("_", now().epochSeconds)
    }.deserializeJson<CommentGetResponse>().also {
        logger.debug { "Got root comment for $type $id: $it" }
    }
}

/**
 * getRoomComment DSL 封装
 * @param builder 参见 [CommentReqDsl]
 */
public suspend fun BiliClient.getComment(
    context: CoroutineContext = this.context,
    builder: CommentReqDsl.() -> Unit,
): CommentGetResponse = with(CommentReqDsl().apply(builder)) {
    logger.debug { "Built CommentReq: $this" }
    getComment(_type ?: error("No type provided"), _id ?: error("No id provided"), _lazy, _page, _count, _sort, context)
}
