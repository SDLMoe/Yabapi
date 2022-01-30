package moe.sdl.yabapi.data.search

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import moe.sdl.yabapi.api.searchByType
import moe.sdl.yabapi.api.searchLive
import moe.sdl.yabapi.enums.search.ArticleOrder
import moe.sdl.yabapi.enums.search.ArticleSearchType
import moe.sdl.yabapi.enums.search.LiveOrder
import moe.sdl.yabapi.enums.search.LiveOrder.ONLINE
import moe.sdl.yabapi.enums.search.OrderSort
import moe.sdl.yabapi.enums.search.OrderSort.DESCENDING
import moe.sdl.yabapi.enums.search.SearchType
import moe.sdl.yabapi.enums.search.SearchType.ARTICLE
import moe.sdl.yabapi.enums.search.SearchType.USER
import moe.sdl.yabapi.enums.search.SearchType.VIDEO
import moe.sdl.yabapi.enums.search.UserLimit
import moe.sdl.yabapi.enums.search.UserLimit.ALL
import moe.sdl.yabapi.enums.search.UserOrder
import moe.sdl.yabapi.enums.search.UserOrder.DEFAULT
import moe.sdl.yabapi.enums.search.VideoDuration
import moe.sdl.yabapi.enums.search.VideoDuration.NO_LIMIT
import moe.sdl.yabapi.enums.search.VideoOrder
import moe.sdl.yabapi.enums.search.VideoOrder.MIXED
import moe.sdl.yabapi.enums.video.All
import moe.sdl.yabapi.enums.video.VideoType

/**
 * 基础搜索參數, 所有搜索类型都通用的参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see [searchByType]
 */
public open class SearchOption(
    public open val type: SearchType,
    public open val page: Int = 1,
    public open val showHighlight: Boolean = false,
) {
    public open fun putToQuery(builder: HttpRequestBuilder) {
        builder.apply {
            parameter("search_type", type.code)
            parameter("page", page)
            parameter("highlight", if (showHighlight) "1" else "0")
        }
    }

    override fun toString(): String = "SearchOption(page=$page, showHighlight=$showHighlight)"
}

/**
 * 视频搜索参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see [searchByType]
 * @see SearchOption
 */
public data class VideoSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
    public val order: VideoOrder = MIXED,
    public val duration: VideoDuration = NO_LIMIT,
    public val videoType: VideoType = All,
) : SearchOption(VIDEO, page, showHighlight) {
    override fun putToQuery(builder: HttpRequestBuilder) {
        super.putToQuery(builder)
        builder.apply {
            parameter("order", order.code)
            parameter("duration", duration.code)
            parameter("tids", videoType.code)
        }
    }
}

/**
 * 直播搜索的基础 `abstract class`, 仅作约束作用
 *
 * @see searchLive
 * @see SearchOption
 */
public abstract class LiveSearchOptionBase(
    public override val type: SearchType,
    public override val page: Int,
    public override val showHighlight: Boolean,
) : SearchOption(type, page, showHighlight)

/**
 * 直播搜索参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see searchLive
 * @see LiveSearchOptionBase
 */
public data class LiveSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
    public val order: LiveOrder = ONLINE,
) : LiveSearchOptionBase(SearchType.LIVE, page, showHighlight) {
    override fun putToQuery(builder: HttpRequestBuilder) {
        super.putToQuery(builder)
        builder.apply {
            parameter("order", order.code)
        }
    }
}

/**
 * 直播用户搜索参数, 暂时没有特殊参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see searchLive
 * @see LiveSearchOptionBase
 */
public data class LiveUserSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
) : LiveSearchOptionBase(SearchType.LIVE_USER, page, showHighlight)

/**
 * 文章搜索参数
 *
 * 强烈建议以 **指名参数** 形式传递
 *
 * @param order 搜索顺序 [ArticleOrder]
 * @param articleType 文章分类 [ArticleSearchType]
 * @see searchLive
 * @see SearchOption
 */
public data class ArticleSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
    public val order: ArticleOrder = ArticleOrder.MIXED,
    public val articleType: ArticleSearchType = ArticleSearchType.NO_LIMIT,
) : SearchOption(ARTICLE, page, showHighlight) {
    override fun putToQuery(builder: HttpRequestBuilder) {
        super.putToQuery(builder)
        builder.apply {
            parameter("order", order.code)
            articleType.code?.let { parameter("category_id", it) }
        }
    }
}

/**
 * 用户搜索参数
 *
 * 强烈建议以 **指名参数** 形式传递
 *
 * @param order 顺序 [UserOrder]
 * @param sort 降序或升序, 仅在 `order` 不为 [UserOrder.DEFAULT] 时生效
 * @param limit 用户类型限制 [UserLimit]
 * @see searchLive
 * @see SearchOption
 */
public data class UserSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
    public val order: UserOrder = DEFAULT,
    public val sort: OrderSort = DESCENDING,
    public val limit: UserLimit = ALL,
) : SearchOption(USER, page, showHighlight) {
    override fun putToQuery(builder: HttpRequestBuilder) {
        super.putToQuery(builder)
        builder.apply {
            if (order != DEFAULT) {
                parameter("order", order.code)
                parameter("order_sort", sort.code)
            }
            parameter("limit", limit.code)
        }
    }
}

/**
 * 动画搜索参数, 暂时没有特殊参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see searchByType
 * @see SearchOption
 */
public data class AnimeSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
) : LiveSearchOptionBase(SearchType.ANIME, page, showHighlight)

/**
 * 剧集搜索参数, 暂时没有特殊参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see [searchByType]
 * @see SearchOption
 */
public data class SeriesSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
) : LiveSearchOptionBase(SearchType.SERIES, page, showHighlight)

/**
 * 话题搜索参数, 暂时没有特殊参数
 *
 * 强烈建议以 **指名参数** 形式传递
 * @see searchByType
 * @see SearchOption
 */
public data class TopicSearchOption(
    public override val page: Int = 1,
    public override val showHighlight: Boolean = false,
) : LiveSearchOptionBase(SearchType.TOPIC, page, showHighlight)
