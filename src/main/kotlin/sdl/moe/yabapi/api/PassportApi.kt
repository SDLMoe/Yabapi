package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.passport.QUERY_CAPTCHA_URL
import sdl.moe.yabapi.data.login.QueryCaptchaResponse

private val logger = KotlinLogging.logger {}

public object PassportApi : BiliApi {
    init {
        BiliClient.registerApi(apiName, this)
    }

    override val apiName: String
        get() = "passport"

    public val BiliClient.passport: BiliClient
        get() = this

    /**
     * 获取验证码
     * @return [QueryCaptchaResponse]
     */
    public suspend fun BiliClient.queryLoginCaptcha(): QueryCaptchaResponse = withContext(Dispatchers.IO) {
        logger.info { "Querying Login Captcha" }
        client.get(QUERY_CAPTCHA_URL) {
            headers.append("Referer", "https://passport.bilibili.com/login")
        }
    }
}
