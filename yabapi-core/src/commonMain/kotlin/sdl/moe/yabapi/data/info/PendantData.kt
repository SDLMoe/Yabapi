// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 挂件信息
 * @param pid 挂件id
 * @param name 名称
 * @param image 图片url
 * @param imageEnhance 頭像框
 * @param imageEnhanceFrame 未知
 * @param expire 可能为过期时间, 但仅返回 `0`
 */
@Serializable
public data class Pendant(
    @SerialName("pid") val pid: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("image_enhance") val imageEnhance: String? = null,
    @SerialName("image_enhance_frame") val imageEnhanceFrame: String? = null,
    @SerialName("expire") val expire: Long? = null,
)
