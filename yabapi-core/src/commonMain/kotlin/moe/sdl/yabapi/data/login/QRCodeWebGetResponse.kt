@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * @param code [GeneralCode]
 * @param status 作用不明
 * @param timestamp 时间戳
 * @param data [QRCodeWebGetResponseData]
 */
@Serializable
public data class QRCodeWebGetResponse(
    @SerialName("code")
    val code: GeneralCode = UNKNOWN,
    @SerialName("status")
    val status: Boolean,
    @SerialName("ts")
    val timestamp: Long,
    val data: QRCodeWebGetResponseData,
)

/**
 * @param url 二维码内容地址
 * @param oauthKey 扫码登录密钥
 */
@Serializable
public data class QRCodeWebGetResponseData(
    val url: String,
    val oauthKey: String,
)
