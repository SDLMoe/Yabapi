package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.data.article.ArticleReadInfo
import sdl.moe.yabapi.enums.feed.FeedType.ARTICLE
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class ArticleCard(
    public val data: ArticleReadInfo,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = ARTICLE.code

        override fun decode(json: Json, data: String): ArticleCard = json.decodeFromString(data)
    }
}
