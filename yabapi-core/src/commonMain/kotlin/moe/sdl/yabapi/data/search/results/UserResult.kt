@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class UserResult(
    @SerialName("type") val type: String,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("uname") val uname: String? = null,
    @SerialName("usign") val bio: String? = null,
    @SerialName("fans") val fans: Int? = null,
    @SerialName("videos") val videoNum: Int? = null,
    @SerialName("upic") val avatar: String? = null,
    @SerialName("face_nft") val faceNft: Int? = null,
    @SerialName("verify_info") val verifyInfo: String? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("gender") val gender: Int? = null,
    @SerialName("is_upuser") val isUploader: Boolean? = null,
    @SerialName("is_live") val isLive: Boolean? = null,
    @SerialName("room_id") val roomId: Int? = null,
    @SerialName("res") val videos: List<UserVideo>,
    @SerialName("official_verify") val officialCertify: OfficialCertify? = null,
    @SerialName("hit_columns") val hitColumns: JsonArray? = null,
    @SerialName("expand") val expand: UserExpandInfo? = null,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "bili_user"
        override fun decode(json: Json, data: JsonObject): UserResult = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class UserVideo(
    @SerialName("aid") val aid: Int? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("pubdate") val pubdate: Long? = null,
    @SerialName("arcurl") val avUrl: String? = null,
    @SerialName("pic") val pic: String? = null,
    @SerialName("play") val play: String? = null,
    @SerialName("dm") val danmaku: Int? = null,
    @SerialName("coin") val coin: Int? = null,
    @SerialName("fav") val collect: Int? = null,
    @SerialName("desc") val desc: String? = null,
    @SerialName("duration") val duration: String? = null,
    @SerialName("is_pay") val isPay: Boolean? = null,
    @SerialName("is_union_video") val isUnionVideo: Boolean? = null,
)

@Serializable
public data class UserExpandInfo(
    @SerialName("is_power_up") val isPowerUp: Boolean? = null, // 是否百大
    @SerialName("system_notice") val systemNotice: String? = null,
)
