@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.search.results.ResultFactory
import moe.sdl.yabapi.data.search.results.SearchResult
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class SearchAllResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SearchAllData? = null,
)

@Serializable
public data class SearchAllData(
    @SerialName("seid") val seid: String,
    @SerialName("page") val page: Int,
    @SerialName("pagesize") val pageSize: Int,
    @SerialName("numResults") val numResults: Int,
    @SerialName("numPages") val numPages: Int,
    @SerialName("suggest_keyword") val suggestKeyword: String,
    @SerialName("rqt_type") val rqtType: String,
    @SerialName("cost_time") val costTime: Map<String, String>,
    @SerialName("exp_list") val expList: Map<String, String>,
    @SerialName("egg_hit") val eggHit: Boolean,
    @SerialName("pageinfo") val pageInfo: Map<String, SearchNumInfo>,
    @SerialName("top_tlist") val typeList: Map<String, Int>,
    @SerialName("show_column") val showColumn: Int,
    @SerialName("show_module_list") val moduleList: List<String>,
    @SerialName("result") val result: List<SearchResultData>,
) {
    public val resultFlatted: List<SearchResult> by lazy {
        result.flatMap { it.data.orEmpty() }
    }
}

@Serializable
public data class SearchNumInfo(
    @SerialName("numResults") val numResults: Int,
    @SerialName("total") val total: Int,
    @SerialName("pages") val pages: Int,
)

@Serializable
public data class SearchResultData(
    @SerialName("result_type") val resultType: String,
    @SerialName("data") public val rawData: List<JsonObject> = emptyList(),
) {
    public val data: List<SearchResult>? by lazy {
        val factory = ResultFactory.map[resultType] ?: return@lazy null
        rawData.map { factory.decode(Yabapi.defaultJson.value, it) }
    }
}
