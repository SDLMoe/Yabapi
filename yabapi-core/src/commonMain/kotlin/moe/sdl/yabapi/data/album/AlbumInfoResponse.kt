@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.Yabapi.defaultJson
import moe.sdl.yabapi.data.album.AlbumResponseCode.SUCCESS
import moe.sdl.yabapi.data.album.AlbumResponseCode.UNKNOWN
import moe.sdl.yabapi.data.info.VipType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class AlbumInfoResponse(
    @SerialName("code") val code: AlbumResponseCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") private val _data: JsonObject? = null,
) {
    public fun getData(json: Json = defaultJson.value): AlbumInfoData? {
        if (code != SUCCESS) return null
        return _data?.let { json.decodeFromJsonElement(_data) }
    }
}

@Serializable
public data class AlbumInfoData(
    @SerialName("user") val user: AlbumUser? = null,
    @SerialName("item") val item: AlbumItem? = null,
)

@Serializable
public data class AlbumUser(
    @SerialName("uid") val uid: Int? = null,
    @SerialName("head_url") val headUrl: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("vip") val vip: AlbumVipInfo? = null,
    @SerialName("upload_count") val uploadCount: String? = null,
)

@Serializable
public data class AlbumVipInfo(
    @SerialName("vipType") val vipType: VipType = VipType.UNKNOWN,
    @SerialName("vipDueDate") val vipDueDate: Long? = null,
    @SerialName("dueRemark") val dueRemark: String? = null,
    @SerialName("accessStatus") val accessStatus: Boolean? = null,
    @SerialName("vipStatus") val vipStatus: Boolean? = null,
    @SerialName("vipStatusWarn") val vipStatusWarn: String? = null,
    @SerialName("themeType") val themeType: Int? = null,
    @SerialName("label") val label: AlbumVipLabel? = null,
)

@Serializable
public data class AlbumVipLabel(
    @SerialName("path") val path: String? = null,
)

@Serializable
public data class AlbumItem(
    @SerialName("biz") val type: AlbumType = AlbumType.UNKNOWN,
    @SerialName("doc_id") val id: Int? = null,
    @SerialName("poster_uid") val authorMid: Int? = null,
    @SerialName("category") val category: String? = null,
    @SerialName("type") val origin: AlbumOrigin = AlbumOrigin.UNKNOWN,
    @SerialName("title") val title: String? = null,
    @SerialName("tags") val tags: List<AlbumTag> = emptyList(),
    @SerialName("pictures") val pictures: List<AlbumPicture> = emptyList(),
    @SerialName("source") val source: JsonElement? = null,
    @SerialName("upload_time") val uploadTime: String? = null,
    @SerialName("upload_timestamp") val uploadTimestamp: Long? = null,
    @SerialName("upload_time_text") val uploadTimeText: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("role") val role: JsonElement? = null,
    @SerialName("settings") val settings: AlbumSetting? = null,
    @SerialName("already_collected") val collected: Boolean? = null,
    @SerialName("already_liked") val liked: Boolean? = null,
    @SerialName("user_status") val userStatus: Int? = null,
    @SerialName("at_control") val atControl: String? = null,
    @SerialName("view_count") val viewCount: Int? = null,
    @SerialName("like_count") val likeCount: Int? = null,
    @SerialName("collect_count") val collectCount: Int? = null,
    @SerialName("verify_status") val verifyStatus: Int? = null,
    @SerialName("already_voted") val voted: Boolean? = null, // 是否已經點讚
    @SerialName("vote_count") val voteCount: Int? = null,
    @SerialName("comment_count") val commentCount: Int? = null,
)

@Serializable
public enum class AlbumOrigin {
    UNKNOWN,

    @SerialName("0")
    ORIGINAL, // 原創

    @SerialName("1")
    DOUJIN, // 同人
    ;
}

@Serializable
public data class AlbumTag(
    @SerialName("tag") val tag: String? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("category") val category: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("text") val text: String? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class AlbumPicture(
    @SerialName("img_src") val url: String? = null,
    @SerialName("img_width") val width: Int? = null,
    @SerialName("img_height") val height: Int? = null,
    @SerialName("img_size") val size: Int? = null, // KiB
)

@Serializable
public data class AlbumSetting(
    @SerialName("copy_forbidden") val copyright: AlbumCopyright = AlbumCopyright.UNKNOWN,
)

@Serializable
public enum class AlbumCopyright {
    UNKNOWN,

    @SerialName("0")
    NOT_CLEAR, // 未設置

    @SerialName("1")
    OPEN_BY_NC, // 開放轉載, 署名禁商

    @SerialName("2")
    REQUEST_BY_NC, // 詢問轉載, 署名禁商

    @SerialName("3")
    REVERSED, // 保留權利, 禁止轉載
}
