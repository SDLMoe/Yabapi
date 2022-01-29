package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.data.article.ArticleReadInfo
import moe.sdl.yabapi.enums.feed.FeedType.ARTICLE
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
