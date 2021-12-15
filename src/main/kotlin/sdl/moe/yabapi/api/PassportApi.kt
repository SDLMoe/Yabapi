package sdl.moe.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.APP_KEY
import sdl.moe.yabapi.consts.APP_SIGN
import sdl.moe.yabapi.consts.passport.QUERY_CAPTCHA_URL
import sdl.moe.yabapi.consts.passport.RSA_GET_APP_URL
import sdl.moe.yabapi.consts.passport.RSA_GET_WEB_URL
import sdl.moe.yabapi.data.login.QueryCaptchaResponse
import sdl.moe.yabapi.data.login.RsaGetResponse
import sdl.moe.yabapi.data.login.RsaGetResponseCode.SIGN_INVALID
import sdl.moe.yabapi.data.login.RsaGetResponseCode.UNKNOWN

private val logger = KotlinLogging.logger {}

public object PassportApi : BiliApi {
    init {
        BiliClient.registerApi(apiName, this)
    }

    override val apiName: String
        get() = "passport"

    @Suppress("unused")
    public val BiliClient.passport: PassportApi
        get() = this@PassportApi

    /**
     * 获取验证码
     * @return [QueryCaptchaResponse]
     */
    @JvmName("queryLoginCaptchaExt")
    public suspend fun BiliClient.queryLoginCaptcha(): QueryCaptchaResponse = withContext(Dispatchers.IO) {
        logger.info { "Querying Login Captcha" }
        client.get<QueryCaptchaResponse>(QUERY_CAPTCHA_URL).also {
            logger.debug { "Query Captcha Response: $it" }
        }
    }

    @JvmName("queryLoginCaptcha")
    public suspend fun queryLoginCaptcha(client: BiliClient): QueryCaptchaResponse = client.queryLoginCaptcha()

    /**
     * 通过 Web 方式获取 RSA 公钥
     * @return [RsaGetResponse]
     */
    @JvmName("getRsaKeyWebExt")
    public suspend fun BiliClient.getRsaKeyWeb(): RsaGetResponse = withContext(Dispatchers.IO) {
        logger.info { "Getting RSA Key by Web method" }
        client.get<RsaGetResponse>(RSA_GET_WEB_URL) {
            parameter("act", "getkey")
        }.also {
            logger.debug { "RSA Get Web Response: $it" }
        }
    }

    @JvmName("getRsaKeyWeb")
    public suspend fun getRsaKeyWeb(client: BiliClient): RsaGetResponse = client.getRsaKeyWeb()

    /**
     * 通过 App 方式获取 RSA 公钥
     * @return [RsaGetResponse]
     */
    @JvmName("getRsaKeyAppExt")
    public suspend fun BiliClient.getRsaKeyApp(
        appKey: String = APP_KEY,
        appSign: String = APP_SIGN,
    ): RsaGetResponse = withContext(Dispatchers.IO) {
        logger.info { "Getting RSA Key by App method" }
        client.post<RsaGetResponse>(RSA_GET_APP_URL) {
            val param = Parameters.build {
                append("appkey", appKey)
                append("sign", appSign)
            }
            body = FormDataContent(param)
        }.also {
            logger.debug { "RSA Get App Response: $it" }
            if (it.code == UNKNOWN) {
                logger.warn { "RSA Get App Response Code is UNKNOWN, with message: ${it.message}" }
            }
            if (it.code == SIGN_INVALID) {
                logger.warn { "RSA Get App Response Code is SIGN_INVALID, with message: ${it.message}" }
            }
        }
    }

    @JvmName("getRsaKeyApp")
    public suspend fun getRsaKeyApp(client: BiliClient): RsaGetResponse = client.getRsaKeyApp()
}
