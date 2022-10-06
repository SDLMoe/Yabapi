package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.data.search.results.ResultFactory
import moe.sdl.yabapi.data.search.results.SearchResult
import kotlin.jvm.JvmInline

@Serializable
public sealed class SearchData {
    public abstract val seid: String?
    public abstract val page: Long?
    public abstract val pageSize: Int?
    public abstract val numResults: Int?
    public abstract val numPages: Int?
    public abstract val suggestKeyword: String?
    public abstract val rqtType: String?
    public abstract val costTime: Map<String, String>
    public abstract val expList: Map<String, String>
    public abstract val eggHit: Boolean?
    public abstract val pageInfo: Map<String, SearchNumInfo>
    public abstract val showColumn: Int?
    public abstract val result: SearchResultData?
}

public sealed interface SearchResultData

@Suppress("NOTHING_TO_INLINE")
internal inline fun List<JsonObject>.toResults(type: String): List<SearchResult>? {
    val factory = ResultFactory.map[type] ?: return null
    return this.map { factory.decode(Yabapi.defaultJson.value, it) }
}

@Serializable
@JvmInline
public value class SearchAllResultData(
    public val value: List<SearchAllResultNode> = emptyList(),
) : SearchResultData

@Serializable
public data class SearchAllResultNode(
    @SerialName("result_type") val resultType: String? = null,
    @SerialName("data") public val rawData: List<JsonObject> = emptyList(),
) {
    public val data: List<SearchResult>? by lazy { resultType?.let { rawData.toResults(it) } }
}

@Serializable
@JvmInline
public value class SearchNormalResultData(
    public val value: List<JsonObject> = emptyList(),
) : SearchResultData

@Serializable
public data class SearchLiveResultData(
    @SerialName("live_room") val rawLiveRoom: List<JsonObject> = emptyList(),
    @SerialName("live_user") val rawLiveUser: List<JsonObject> = emptyList(),
) : SearchResultData {
    public val liveRoom: List<SearchResult>? by lazy { rawLiveRoom.toResults("live_room") }
    public val liveUser: List<SearchResult>? by lazy { rawLiveUser.toResults("live_user") }
}
