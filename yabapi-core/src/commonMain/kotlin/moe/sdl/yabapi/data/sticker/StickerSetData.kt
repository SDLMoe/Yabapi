@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.sticker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.sticker.StickerType.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * **表情包**的数据类
 * @param id 表情包的ID
 * @param name 表情包的名称
 * @param iconUrl 表情包的图标URL
 * @param createTime 表情包的创建时间
 * @param type 表情包的类型
 * @param attr 未知
 * @param metadata 元數據
 * @param stickerList 表情列表
 * @param flags [StickerSetFlags]
 */
@Serializable
public data class StickerSetData(
    @SerialName("id") val id: Int? = null,
    @SerialName("text") val name: String? = null,
    @SerialName("url") val iconUrl: String? = null,
    @SerialName("mtime") val createTime: Long? = null,
    @SerialName("type") val type: StickerType = UNKNOWN,
    @SerialName("attr") val attr: Int? = null,
    @SerialName("meta") val metadata: StickerSetMetadata? = null,
    @SerialName("emote") val stickerList: List<StickerData> = emptyList(),
    @SerialName("flags") val flags: StickerSetFlags? = null,
)

/**
 * 表情包元數據
 * @param size 表情包的尺寸 [StickerSize]
 * @param itemId 購買物品 ID
 * @param itemUrl 購買頁面 URL
 */
@Serializable
public data class StickerSetMetadata(
    @SerialName("size") val size: StickerSize = StickerSize.UNKNOWN,
    @SerialName("item_id") val itemId: Int? = null,
    @SerialName("item_url") val itemUrl: String? = null,
    @SerialName("vip_no_access_text") val needVipText: String? = null,
)

@Serializable
public data class StickerSetFlags(
    @SerialName("added") val isAdded: Boolean? = null,
    @SerialName("preview") val isPreview: Boolean? = null,
    @SerialName("no_access") val noAccess: Boolean? = null,
)
