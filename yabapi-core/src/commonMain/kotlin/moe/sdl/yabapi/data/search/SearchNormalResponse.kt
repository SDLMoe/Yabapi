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
    @SerialName("seid") override val seid: String? = null,
    @SerialName("page") override val page: Int? = null,
    @SerialName("pagesize") override val pageSize: Int? = null,
    @SerialName("numResults") override val numResults: Int? = null,
    @SerialName("numPages") override val numPages: Int? = null,
    @SerialName("suggest_keyword") override val suggestKeyword: String? = null,
    @SerialName("rqt_type") override val rqtType: String? = null,
    @SerialName("cost_time") override val costTime: Map<String, String> = emptyMap(),
    @SerialName("exp_list") override val expList: Map<String, String> = emptyMap(),
    @SerialName("egg_hit") override val eggHit: Boolean? = null,
    @SerialName("pageinfo") override val pageInfo: Map<String, SearchNumInfo> = emptyMap(),
    @SerialName("show_column") override val showColumn: Int? = null,
    @SerialName("result") override val result: SearchNormalResultData? = null,
) : SearchData() {
    public val data: List<SearchResult>? by lazy {
        if (result?.value?.isNotEmpty() == true) {
            val type = result.value.first()["type"]?.jsonPrimitive?.contentOrNull
                ?: error("Search Result Field [type] not available")
            result.value.toResults(type)
        } else emptyList()
    }
}
