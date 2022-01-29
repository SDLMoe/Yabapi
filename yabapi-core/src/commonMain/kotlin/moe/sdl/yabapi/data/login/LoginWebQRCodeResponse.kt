@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.serializer
import moe.sdl.yabapi.Yabapi.defaultJson
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.login.LoginWebQRCodeResponseCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 登录回调数据
 *
 * @param code [GeneralCode]
 * @param message 错误信息 正确无
 * @param timestamp 扫码时间 错误无
 * @param status 扫码是否成功
 * @param rawData 原始扫码结果
 * @property dataWhenSuccess 扫码成功时的数据
 * @property dataWhenFailed 扫码失败时的数据
 */
@Serializable
public data class LoginWebQRCodeResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ts") val timestamp: Long? = null,
    @SerialName("status") val status: Boolean,
    @SerialName("data") private val rawData: JsonElement,
) {
    public val dataWhenSuccess: LoginWebQRCodeResponseData? by lazy {
        if (code == GeneralCode.SUCCESS) {
            val url = rawData.jsonObject["url"]?.jsonPrimitive?.content
            if (url != null) {
                LoginWebQRCodeResponseData(url)
            } else null
        } else null
    }

    public val dataWhenFailed: LoginWebQRCodeResponseCode? by lazy {
        if (code != GeneralCode.SUCCESS) {
            try {
                val serializer = serializer<LoginWebQRCodeResponseCode>()
                defaultJson.value.decodeFromJsonElement(serializer, rawData)
            } catch (_: IllegalArgumentException) {
                UNKNOWN
            }
        } else null
    }
}

/**
 * @param url 游戏分站 Url
 */
@Serializable
public data class LoginWebQRCodeResponseData(
    val url: String,
) {
    public constructor(jsonObject: JsonObject) : this(
        jsonObject["url"]?.jsonPrimitive?.content ?: throw IllegalArgumentException("Unknown Json Object $jsonObject"),
    )
}

@Suppress("MagicNumber")
@Serializable
public enum class LoginWebQRCodeResponseCode {
    UNKNOWN,

    @SerialName("-1")
    KEY_ERROR,

    @SerialName("-2")
    KEY_EXPIRED,

    @SerialName("-4")
    NOT_SCAN,

    @SerialName("-5")
    NOT_CONFIRM;
}
