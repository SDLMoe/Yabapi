package moe.sdl.yabapi.api

// private val logger by lazy { Logger("DanmakuApiJvm") }

// public suspend fun BiliClient.getDanmakuMetadata(
//     cid: Int,
//     aid: Int? = null,
//     type: DanmakuType = VIDEO,
//     context: CoroutineContext = this.context,
// ): DanmakuWebMetadata = withContext(context) {
//     val showAid = aid?.let { " (av$it)" } ?: ""
//     logger.debug { "Getting danmaku metadata for cid $cid$showAid..." }
//     client.get<ByteArray>(VIDEO_DANMAKU_WEB_URL) {
//         parameter("type", type.code)
//         parameter("oid", cid)
//         aid?.let { parameter("pid", aid) }
//     }.let { DanmakuWebMetadata.parseFrom(it) }
// }
//
// public suspend inline fun BiliClient.getDanmakuMetadata(
//     cid: Int, bid: String, type: DanmakuType = VIDEO, context: CoroutineContext = this.context,
// ): DanmakuWebMetadata = getDanmakuMetadata(cid, bid.avInt, type, context)
