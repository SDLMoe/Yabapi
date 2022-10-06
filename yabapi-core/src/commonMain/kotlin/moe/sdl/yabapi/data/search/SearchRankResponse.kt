package moe.sdl.yabapi.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class SearchRankResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("exp_str") val expStr: String? = null,
    @SerialName("cost") val cost: Map<String, String> = mapOf(),
    @SerialName("seid") val seid: String? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("list") val list: List<SearchRankNode>? = null,
)

@Serializable
public data class SearchRankNode(
    @SerialName("status") val status: String? = null,
    @SerialName("hot_id") val hotId: Long? = null,
    @SerialName("keyword") val keyword: String? = null,
    @SerialName("resource_id") val resourceId: Long? = null,
    @SerialName("goto_type") val gotoType: Int? = null,
    @SerialName("show_name") val showName: String? = null,
    @SerialName("pos") val pos: Int? = null,
    @SerialName("word_type") val wordType: Int? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("goto_value") val gotoValue: String? = null,
    @SerialName("name_type") val nameType: String? = null,
    @SerialName("icon") val icon: String? = null,
)
