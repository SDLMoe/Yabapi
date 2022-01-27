@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import sdl.moe.yabapi.Yabapi.defaultJson
import sdl.moe.yabapi.data.album.AlbumResponseCode.SUCCESS
import sdl.moe.yabapi.data.album.AlbumResponseCode.UNKNOWN
import sdl.moe.yabapi.data.info.VipType
import sdl.moe.yabapi.serializer.BooleanJsSerializer

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
    @SerialName("user") val user: AlbumUser,
    @SerialName("item") val item: AlbumItem,
)

@Serializable
public data class AlbumUser(
    @SerialName("uid") val uid: Int,
    @SerialName("head_url") val headUrl: String,
    @SerialName("name") val name: String,
    @SerialName("vip") val vip: AlbumVipInfo,
    @SerialName("upload_count") val uploadCount: String,
)

@Serializable
public data class AlbumVipInfo(
    @SerialName("vipType") val vipType: VipType = VipType.UNKNOWN,
    @SerialName("vipDueDate") val vipDueDate: Long,
    @SerialName("dueRemark") val dueRemark: String,
    @SerialName("accessStatus") val accessStatus: Boolean,
    @SerialName("vipStatus") val vipStatus: Boolean,
    @SerialName("vipStatusWarn") val vipStatusWarn: String,
    @SerialName("themeType") val themeType: Int,
    @SerialName("label") val label: AlbumVipLabel,
)

@Serializable
public data class AlbumVipLabel(
    @SerialName("path") val path: String,
)

@Serializable
public data class AlbumItem(
    @SerialName("biz") val type: AlbumType = AlbumType.UNKNOWN,
    @SerialName("doc_id") val id: Int,
    @SerialName("poster_uid") val authorMid: Int,
    @SerialName("category") val category: String,
    @SerialName("type") val origin: AlbumOrigin = AlbumOrigin.UNKNOWN,
    @SerialName("title") val title: String,
    @SerialName("tags") val tags: List<AlbumTag> = emptyList(),
    @SerialName("pictures") val pictures: List<AlbumPicture> = emptyList(),
    @SerialName("source") val source: JsonElement? = null,
    @SerialName("upload_time") val uploadTime: String,
    @SerialName("upload_timestamp") val uploadTimestamp: Long,
    @SerialName("upload_time_text") val uploadTimeText: String,
    @SerialName("description") val description: String,
    @SerialName("role") val role: JsonElement? = null,
    @SerialName("settings") val settings: AlbumSetting,
    @SerialName("already_collected") val collected: Boolean,
    @SerialName("already_liked") val liked: Boolean,
    @SerialName("user_status") val userStatus: Int,
    @SerialName("at_control") val atControl: String,
    @SerialName("view_count") val viewCount: Int,
    @SerialName("like_count") val likeCount: Int,
    @SerialName("collect_count") val collectCount: Int,
    @SerialName("verify_status") val verifyStatus: Int,
    @SerialName("already_voted") val voted: Boolean, // 是否已經點讚
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("comment_count") val commentCount: Int,
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
    @SerialName("tag") val tag: String,
    @SerialName("type") val type: Int,
    @SerialName("category") val category: String,
    @SerialName("link") val link: String? = null,
    @SerialName("text") val text: String,
    @SerialName("name") val name: String,
)

@Serializable
public data class AlbumPicture(
    @SerialName("img_src") val url: String,
    @SerialName("img_width") val width: Int,
    @SerialName("img_height") val height: Int,
    @SerialName("img_size") val size: Int, // KiB
)

@Serializable
public data class AlbumSetting(
    @SerialName("copy_forbidden") val copyright: AlbumCopyright,
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
