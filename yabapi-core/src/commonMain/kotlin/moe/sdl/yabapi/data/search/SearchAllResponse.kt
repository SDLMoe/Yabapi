@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
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
    @SerialName("seid") override val seid: String? = null,
    @SerialName("page") override val page: Int? = null,
    @SerialName("pagesize") override val pageSize: Int? = null,
    @SerialName("numResults") override val numResults: Int? = null,
    @SerialName("numPages") override val numPages: Int? = null,
    @SerialName("suggest_keyword") override val suggestKeyword: String? = null,
    @SerialName("rqt_type") override val rqtType: String? = null,
    @SerialName("cost_time") override val costTime: Map<String, String> = mapOf(),
    @SerialName("exp_list") override val expList: Map<String, String> = mapOf(),
    @SerialName("egg_hit") override val eggHit: Boolean? = null,
    @SerialName("pageinfo") override val pageInfo: Map<String, SearchNumInfo> = mapOf(),
    @SerialName("top_tlist") val typeList: Map<String, Int> = mapOf(),
    @SerialName("show_column") override val showColumn: Int? = null,
    @SerialName("show_module_list") val moduleList: List<String> = emptyList(),
    @SerialName("result") override val result: SearchAllResultData? = null,
) : SearchData() {
    public val resultFlatted: List<SearchResult> by lazy {
        result?.value?.flatMap { it.data.orEmpty() } ?: emptyList()
    }
}

@Serializable
public data class SearchNumInfo(
    @SerialName("numPages") val numPages: Int? = null,
    @SerialName("numResults") val numResults: Int? = null,
    @SerialName("total") val total: Int? = null,
    @SerialName("pages") val pages: Int? = null,
)
