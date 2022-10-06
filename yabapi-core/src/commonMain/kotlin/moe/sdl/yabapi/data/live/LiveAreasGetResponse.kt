package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class LiveAreasGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: List<LiveArea> = emptyList(),
)

@Serializable
public data class LiveArea(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("list") val list: List<LiveSubArea> = emptyList(),
)

@Serializable
public data class LiveSubArea(
    @SerialName("id") val id: Long? = null,
    @SerialName("parent_id") val parentId: Long? = null,
    @SerialName("old_area_id") val oldAreaId: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("act_id") val actId: String? = null,
    @SerialName("pk_status") val pkStatus: String? = null,
    @SerialName("hot_status") val hotStatus: Int? = null,
    @SerialName("lock_status") val lockStatus: String? = null,
    @SerialName("pic") val pic: String? = null,
    @SerialName("cate_id") val cateId: String? = null,
    @SerialName("complex_area_name") val complexAreaName: String? = null,
    @SerialName("parent_name") val parentName: String? = null,
    @SerialName("area_type") val areaType: Int? = null,
)
