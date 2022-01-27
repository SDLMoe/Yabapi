package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.data.article.ArticleReadInfo
import kotlin.jvm.JvmInline

// import kotlinx.serialization.Serializable

// @Serializable
// public data class ArticleCard(
//
// )

@Serializable
@JvmInline
public value class ArticleCard(
    public val data: ArticleReadInfo,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = 64

        override fun decode(json: Json, data: String): ArticleCard = json.decodeFromString(data)
    }
}
