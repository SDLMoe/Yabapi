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
    @SerialName("mid") val mid: Int,
    @SerialName("uname") val uname: String,
    @SerialName("usign") val bio: String,
    @SerialName("fans") val fans: Int,
    @SerialName("videos") val videoNum: Int,
    @SerialName("upic") val avatar: String,
    @SerialName("face_nft") val faceNft: Int,
    @SerialName("verify_info") val verifyInfo: String,
    @SerialName("level") val level: Int,
    @SerialName("gender") val gender: Int,
    @SerialName("is_upuser") val isUploader: Boolean,
    @SerialName("is_live") val isLive: Boolean,
    @SerialName("room_id") val roomId: Int,
    @SerialName("res") val videos: List<UserVideo>,
    @SerialName("official_verify") val officialCertify: OfficialCertify,
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
    @SerialName("aid") val aid: Int,
    @SerialName("bvid") val bvid: String,
    @SerialName("title") val title: String,
    @SerialName("pubdate") val pubdate: Long,
    @SerialName("arcurl") val avUrl: String,
    @SerialName("pic") val pic: String,
    @SerialName("play") val play: String,
    @SerialName("dm") val danmaku: Int,
    @SerialName("coin") val coin: Int,
    @SerialName("fav") val collect: Int,
    @SerialName("desc") val desc: String,
    @SerialName("duration") val duration: String,
    @SerialName("is_pay") val isPay: Boolean,
    @SerialName("is_union_video") val isUnionVideo: Boolean,
)

@Serializable
public data class UserExpandInfo(
    @SerialName("is_power_up") val isPowerUp: Boolean, // 是否百大
    @SerialName("system_notice") val systemNotice: String? = null,
)
