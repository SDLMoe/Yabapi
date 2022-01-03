// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.sticker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.sticker.StickerType.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

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
    @SerialName("id") val id: Int,
    @SerialName("text") val name: String,
    @SerialName("url") val iconUrl: String,
    @SerialName("mtime") val createTime: Long,
    @SerialName("type") val type: StickerType = UNKNOWN,
    @SerialName("attr") val attr: Int,
    @SerialName("meta") val metadata: StickerSetMetadata,
    @SerialName("emote") val stickerList: List<StickerData>,
    @SerialName("flags") val flags: StickerSetFlags,
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
    @SerialName("item_id") val itemId: Int,
    @SerialName("item_url") val itemUrl: String? = null,
    @SerialName("vip_no_access_text") val needVipText: String? = null,
)

@Serializable
public data class StickerSetFlags(
    @SerialName("added") val isAdded: Boolean? = null,
    @SerialName("preview") val isPreview: Boolean? = null,
    @SerialName("no_access") val noAccess: Boolean? = null,
)
