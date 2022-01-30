@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.search.results.SearchResult
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class SearchNormalResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SearchNormalData? = null,
) {
    val result: List<SearchResult>?
        get() = data?.data
}

@Serializable
public data class SearchNormalData(
    @SerialName("seid") override val seid: String,
    @SerialName("page") override val page: Int,
    @SerialName("pagesize") override val pageSize: Int,
    @SerialName("numResults") override val numResults: Int,
    @SerialName("numPages") override val numPages: Int,
    @SerialName("suggest_keyword") override val suggestKeyword: String,
    @SerialName("rqt_type") override val rqtType: String,
    @SerialName("cost_time") override val costTime: Map<String, String>,
    @SerialName("exp_list") override val expList: Map<String, String>,
    @SerialName("egg_hit") override val eggHit: Boolean,
    @SerialName("pageinfo") override val pageInfo: Map<String, SearchNumInfo> = mapOf(),
    // @SerialName("top_tlist") override val typeList: Map<String, Int>,
    @SerialName("show_column") override val showColumn: Int,
    // @SerialName("show_module_list") override val moduleList: List<String>,
    @SerialName("result") override val result: SearchNormalResultData,
) : SearchData() {
    public val data: List<SearchResult>? by lazy {
        if (result.value.isNotEmpty()) {
            val type = result.value.first()["type"]?.jsonPrimitive?.contentOrNull
                ?: error("Search Result Field [type] not available")
            result.value.toResults(type)
        } else emptyList()
    }
}
