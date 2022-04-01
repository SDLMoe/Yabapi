@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.comment

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNames
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.comment.CommentResponseCode.UNKNOWN
import moe.sdl.yabapi.json
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.util.Logger
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("RankingApi") }

@Serializable
public data class CommentGetResponse(
    @SerialName("code") val code: CommentResponseCode? = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: CommentTree? = null,
)

@Serializable
public data class CommentTree @OptIn(ExperimentalSerializationApi::class) constructor(
    @JsonNames("page", "cursor") private val _cursor: JsonElement? = null,
    @SerialName("hots") val hots: List<Comment> = emptyList(),
    @SerialName("notice") val notice: Notice? = null,
    @SerialName("replies") val replies: List<Comment>? = null,
    @SerialName("top") val top: Top? = null,
    @SerialName("top_replies") val topReplies: List<Comment> = emptyList(),
    @SerialName("lottery_card") val lotteryCard: JsonElement? = null,
    @SerialName("folder") val folder: CommentFolder? = null,
    @SerialName("up_selection") val upSelection: UpSelection? = null,
    @SerialName("cm") val cm: JsonElement? = null,
    @SerialName("cm_info") val cmInfo: JsonElement? = null,
    @SerialName("effects") val effects: JsonElement? = null,
    @SerialName("assist") val assist: Int? = null,
    @SerialName("mode") val mode: CommentSort? = null,
    @SerialName("support_mode") val supportMode: List<Int> = emptyList(),
    @SerialName("blacklist") val blacklist: Int? = null,
    @SerialName("vote") val vote: Int? = null,
    @SerialName("lottery") val lottery: Int? = null,
    @SerialName("config") val config: Config? = null,
    @SerialName("upper") val upper: Upper? = null,
    @SerialName("show_bvid") val showBvid: Boolean? = null,
    @SerialName("control") val control: CommentControl? = null,
    @SerialName("note") val note: Int? = null,
    @SerialName("callbacks") val callbacks: JsonElement? = null,
) {
    /**
     * 如果是懒加载, 那么返回此对象
     */
    public val cursor: Cursor? by lazy {
        _cursor?.let {
            runCatching {
                json.decodeFromJsonElement<Cursor?>(it)
            }.onFailure {
                logger.warn(it) { "Failed to deserialize cursor object" }
            }.getOrNull()
        }
    }

    /**
     * 如果不是懒加载，那么返回此对象
     */
    public val page: Page? by lazy {
        _cursor?.let {
            runCatching {
                json.decodeFromJsonElement<Page?>(it)
            }.onFailure {
                logger.warn(it) { "Failed to deserialize page object" }
            }.getOrNull()
        }
    }

    @Serializable
    public data class Page(
        @SerialName("num") val num: Int? = null,
        @SerialName("size") val size: Int? = null,
        @SerialName("count") val count: Int? = null,
        @SerialName("acount") val acount: Int? = null,
    )

    @Serializable
    public data class Cursor(
        @SerialName("all_count") val all: Int? = null,
        @SerialName("is_begin") val isBegin: Boolean? = null,
        @SerialName("prev") val prev: Int? = null,
        @SerialName("next") val next: Int? = null,
        @SerialName("is_end") val isEnd: Boolean? = null,
        @SerialName("mode") val sort: CommentSort? = null,
        @SerialName("show_type") val showType: Int? = null,
        @SerialName("support_mode") val supportMode: List<Int> = emptyList(),
        @SerialName("name") val name: String? = null,
    )

    @Serializable
    public data class Notice(
        @SerialName("content") val content: String? = null,
        @SerialName("id") val id: Int? = null,
        @SerialName("link") val link: String? = null,
        @SerialName("title") val title: String? = null,
    )

    /**
     * 置顶条目
     */
    @Serializable
    public data class Top(
        @SerialName("admin") val admin: Comment? = null, // 管理员置顶条目
        @SerialName("upper") val upper: Comment? = null, // UP 主置顶条目
        @SerialName("vote") val vote: Comment? = null, // 投票置顶条目
    )

    @Serializable
    public data class UpSelection(
        @SerialName("pending_count") val pendingCount: Int? = null,
        @SerialName("ignore_count") val ignoreCount: Int? = null,
    )

    @Serializable
    public data class Config(
        @SerialName("showtopic") val showTopic: Boolean? = null,
        @SerialName("show_up_flag") val showUpFlag: Boolean? = null,
        @SerialName("read_only") val readOnly: Boolean? = null,
    )

    @Serializable
    public data class Upper(
        @SerialName("mid") val mid: Int? = null,
        @SerialName("top") val top: JsonElement? = null,
        @SerialName("vote") val vote: JsonElement? = null,
    )

    @Serializable
    public data class CommentControl(
        @SerialName("input_disable") val inputDisable: Boolean? = null,
        @SerialName("root_input_text") val rootInputText: String? = null,
        @SerialName("child_input_text") val childInputText: String? = null,
        @SerialName("giveup_input_text") val giveUpInputText: String? = null,
        @SerialName("bg_text") val backgroundText: String? = null,
        @SerialName("web_selection") val webSelection: Boolean? = null,
        @SerialName("answer_guide_text") val answerGuidText: String? = null,
        @SerialName("answer_guide_icon_url") val answerGuideIconUrl: String? = null,
        @SerialName("answer_guide_ios_url") val answerGuideIosUrl: String? = null,
        @SerialName("answer_guide_android_url") val answerGuideAndroidUrl: String? = null,
        @SerialName("show_type") val showType: Int? = null,
        @SerialName("show_text") val showText: String? = null,
        @SerialName("disable_jump_emote") val disableJumpEmote: Boolean? = null,
    )
}
