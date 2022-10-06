@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class SearchLiveResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SearchLiveData? = null,
)

@Serializable
public data class SearchLiveData(
    @SerialName("seid") override val seid: String? = null,
    @SerialName("page") override val page: Long? = null,
    @SerialName("pagesize") override val pageSize: Int? = null,
    @SerialName("numResults") override val numResults: Int? = null,
    @SerialName("numPages") override val numPages: Int? = null,
    @SerialName("suggest_keyword") override val suggestKeyword: String? = null,
    @SerialName("rqt_type") override val rqtType: String? = null,
    @SerialName("cost_time") override val costTime: Map<String, String>,
    @SerialName("exp_list") override val expList: Map<String, String> = emptyMap(),
    @SerialName("egg_hit") override val eggHit: Boolean? = null,
    @SerialName("pageinfo") override val pageInfo: Map<String, SearchNumInfo> = mapOf(),
    // @SerialName("top_tlist") override val typeList: Map<String, Int>,
    @SerialName("show_column") override val showColumn: Int? = null,
    // @SerialName("show_module_list") override val moduleList: List<String>,
    @SerialName("result") override val result: SearchLiveResultData? = null,
) : SearchData()
