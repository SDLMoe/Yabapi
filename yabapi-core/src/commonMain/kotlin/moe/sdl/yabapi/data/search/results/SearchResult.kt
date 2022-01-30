package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

public sealed interface SearchResult

public sealed class ResultFactory {
    public abstract val code: String
    public abstract fun decode(json: Json, data: JsonObject): SearchResult

    public companion object {
        private val factories by lazy {
            listOf(
                ActivityResult,
                ArticleResult,
                BangumiResult,
                GameResult,
                LiveRoomResult,
                LiveUserResult,
                PgcResultFactory,
                TopicResult,
                UserResult,
                VideoResult,
            )
        }

        public val map: Map<String, ResultFactory> by lazy { factories.associateBy { it.code } }
    }
}
