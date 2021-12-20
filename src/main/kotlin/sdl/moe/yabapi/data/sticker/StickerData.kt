// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.sticker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param id 表情 ID
 * @param packageId 表情包 ID
 * @param text 表情轉義文字 僅 [StickerType.EMOTICON]
 * @param url 表情圖片網址 僅 [StickerType.EMOTICON]
 * @param createdTime 表情創建時間
 * @param type 表情類型 [StickerType]
 * @param attr 不明
 * @param metadata 元數據 [StickerMetadata]
 */
@Serializable
public data class StickerData(
    @SerialName("id") val id: Int,
    @SerialName("packageId") val packageId: Int,
    @SerialName("text") val text: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("mtime") val createdTime: Long,
    @SerialName("type") val type: StickerType,
    @SerialName("attr") val attr: Int,
    @SerialName("meta") val metadata: StickerMetadata,
    @SerialName("flags") val flags: StickerFlags? = null,
)

/**
 * 表情元數據
 * @param size 表情尺寸 [StickerSize]
 * @param alias 表情別名
 */
@Serializable
public data class StickerMetadata(
    @SerialName("size") val size: StickerSize,
    @SerialName("alias") val alias: String,
)

@Serializable
public data class StickerFlags(
    @SerialName("no_access") val notAccess: Boolean,
)
