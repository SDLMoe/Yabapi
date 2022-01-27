package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

public sealed interface FeedCard

public abstract class FeedCardFactory {
    public abstract val code: Int
    public abstract fun decode(json: Json, data: String): FeedCard

    @ThreadLocal
    public companion object {
        private val factories by lazy {
            listOf(
                ImageCard,
                TextCard,
                RepostCard,
            )
        }

        public val map: Map<Int, FeedCardFactory> by lazy {
            factories.associateBy { it.code }
        }
    }
}
