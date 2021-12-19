// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import mu.KotlinLogging
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer
import sdl.moe.yabapi.serializer.data.login.LoginWebQRCodeResponseFailedCodeSerializer
import sdl.moe.yabapi.serializer.enumFromStringWithFallback

private val logger = KotlinLogging.logger {}

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
    public var dataWhenSuccess: LoginWebQRCodeResponseData? = null
        get() {
            if (field == null && code == GeneralCode.SUCCESS) initData()
            return field
        }
        private set
    public var dataWhenFailed: LoginWebQRCodeResponseFailedCode? = null
        get() {
            if (field == null && code != GeneralCode.SUCCESS) initData()
            return field
        }
        private set

    /**
     * 返回 [Triple]
     *
     * [Boolean] 是否成功
     *
     * Sample:
     *
     * ```
     * val client = BiliClient()
     * val response = client.loginWebQRCodeInteractive()
     * val isSuccess = response.initData()
     * if (isSuccess) println(response.dataWhenSuccess)
     * ```
     */
    public fun initData(): Boolean {
        var isSuccess = false
        try {
            val url = rawData.jsonObject["url"]?.jsonPrimitive?.content
            if (url != null) {
                dataWhenSuccess = LoginWebQRCodeResponseData(url)
                isSuccess = true
            }
        } catch (_: IllegalArgumentException) {
            isSuccess = false
            logger.debug { "Failed to encode dataWhenSuccess" }
        }
        try {
            dataWhenFailed = enumFromStringWithFallback(
                rawData.jsonPrimitive.jsonPrimitive.intOrNull.toString(), LoginWebQRCodeResponseFailedCode.UNKNOWN
            )
        } catch (_: IllegalArgumentException) {
            isSuccess = false
            logger.debug { "Failed to encode dataWhenFailed" }
        }
        if (dataWhenSuccess == null && dataWhenFailed == null) {
            throw IllegalArgumentException("Unknown Json Object $rawData")
        }
        return isSuccess
    }
}

/**
 * @param url 游戏分站 Url
 */
@Serializable
public data class LoginWebQRCodeResponseData(
    val url: String
) {
    public constructor(jsonObject: JsonObject) : this(
        jsonObject["url"]?.jsonPrimitive?.content ?: throw IllegalArgumentException("Unknown Json Object $jsonObject"),
    )
}

@Suppress("MagicNumber")
@Serializable(with = LoginWebQRCodeResponseFailedCodeSerializer::class)
public enum class LoginWebQRCodeResponseFailedCode {
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
