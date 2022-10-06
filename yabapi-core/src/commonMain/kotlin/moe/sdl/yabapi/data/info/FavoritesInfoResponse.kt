@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class FavoritesInfoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: FavoritesInfoData? = null,
)

@Serializable
public data class FavoritesInfoData(
    @SerialName("id") val id: Long? = null,
    @SerialName("fid") val fid: Long? = null,
    @SerialName("mid") val mid: Long? = null,
    @SerialName("attr") val attr: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("upper") val owner: FavoritesOwner? = null,
    @SerialName("cover_type") val coverType: Int? = null,
    @SerialName("cnt_info") val countInfo: FavoritesCountInfo? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("intro") val intro: String? = null,
    @SerialName("ctime") val ctime: Long? = null,
    @SerialName("mtime") val mtime: Long? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("fav_state") val favState: Boolean? = null,
    @SerialName("like_state") val likeState: Boolean? = null,
    @SerialName("media_count") val mediaCount: Int? = null,
) {
    @Serializable
    public data class FavoritesOwner(
        @SerialName("mid") val mid: Long? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("face") val avatar: String? = null,
        @SerialName("followed") val followed: Boolean? = null,
        @SerialName("vip_type") val vipType: VipType = VipType.UNKNOWN,
        @SerialName("vip_statue") val vipStatus: VipStatus = VipStatus.UNKNOWN,
    )
}

@Serializable
public data class FavoritesCountInfo(
    @SerialName("collect") val collect: Int? = null,
    @SerialName("play") val play: Int? = null,
    @SerialName("thumb_up") val thumbUp: Int? = null,
    @SerialName("share") val share: Int? = null,
)
