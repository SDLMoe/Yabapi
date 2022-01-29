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
    @SerialName("data") val data: List<LiveArea>,
)

@Serializable
public data class LiveArea(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("list") val list: List<LiveSubArea>,
)

@Serializable
public data class LiveSubArea(
    @SerialName("id") val id: Int,
    @SerialName("parent_id") val parentId: Int,
    @SerialName("old_area_id") val oldAreaId: Int,
    @SerialName("name") val name: String,
    @SerialName("act_id") val actId: String,
    @SerialName("pk_status") val pkStatus: String,
    @SerialName("hot_status") val hotStatus: Int,
    @SerialName("lock_status") val lockStatus: String,
    @SerialName("pic") val pic: String,
    @SerialName("cate_id") val cateId: String? = null,
    @SerialName("complex_area_name") val complexAreaName: String,
    @SerialName("parent_name") val parentName: String,
    @SerialName("area_type") val areaType: Int,
)
