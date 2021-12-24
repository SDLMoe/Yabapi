// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.sticker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.sticker.StickerType.UNKNOWN

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
    @SerialName("package_id") val packageId: Int,
    @SerialName("text") val text: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("gif_url") val gifUrl: String? = null,
    @SerialName("mtime") val createdTime: Long,
    @SerialName("type") val type: StickerType = UNKNOWN,
    @SerialName("attr") val attr: Int,
    @SerialName("meta") val metadata: StickerMetadata,
    @SerialName("flags") val flags: StickerFlags? = null,
    @SerialName("activity") val activity: StickerActivity? = null,
)

/**
 * 表情元數據
 * @param size 表情尺寸 [StickerSize]
 * @param alias 表情別名
 */
@Serializable
public data class StickerMetadata(
    @SerialName("size") val size: StickerSize = StickerSize.UNKNOWN,
    @SerialName("alias") val alias: String? = null,
    @SerialName("suggest") val suggest: List<String> = emptyList(),
    @SerialName("gif_url") val gifUrl: String? = null,
)

@Serializable
public data class StickerFlags(
    @SerialName("unlocked") val unlocked: Boolean,
)

@Serializable
public data class StickerActivity(
    @SerialName("title") val title: String,
    @SerialName("start_time") val startTime: Long,
    @SerialName("end_time") val endTime: Long,
    @SerialName("jump_url") val jumpUrl: String,
    @SerialName("unlock_descs") val unlockDescription: List<String> = listOf(),
    @SerialName("jump_btn_desc") val buttonDescription: String? = null,
)
