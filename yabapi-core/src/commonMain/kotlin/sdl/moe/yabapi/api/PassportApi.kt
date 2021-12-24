// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.api.PassportApi.getCaptcha
import sdl.moe.yabapi.api.PassportApi.loginWebSMS
import sdl.moe.yabapi.consts.passport.GET_CALLING_CODE_URL
import sdl.moe.yabapi.consts.passport.LOGIN_QRCODE_GET_WEB_URL
import sdl.moe.yabapi.consts.passport.LOGIN_WEB_QRCODE_URL
import sdl.moe.yabapi.consts.passport.LOGIN_WEB_SMS_URL
import sdl.moe.yabapi.consts.passport.LOGIN_WEB_URL
import sdl.moe.yabapi.consts.passport.LOG_OUT_URL
import sdl.moe.yabapi.consts.passport.QUERY_CAPTCHA_URL
import sdl.moe.yabapi.consts.passport.RSA_GET_WEB_URL
import sdl.moe.yabapi.consts.passport.SEND_SMS_URL
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.login.CallingCodeGetResponse
import sdl.moe.yabapi.data.login.CallingCodeNode
import sdl.moe.yabapi.data.login.GetCaptchaResponse
import sdl.moe.yabapi.data.login.LogOutResponse
import sdl.moe.yabapi.data.login.LoginWebQRCodeResponse
import sdl.moe.yabapi.data.login.LoginWebResponse
import sdl.moe.yabapi.data.login.LoginWebResponseCode
import sdl.moe.yabapi.data.login.LoginWebSMSResponse
import sdl.moe.yabapi.data.login.LoginWebSMSResponseCode
import sdl.moe.yabapi.data.login.QRCodeWebGetResponse
import sdl.moe.yabapi.data.login.RsaGetResponse
import sdl.moe.yabapi.data.login.SendSMSResponse
import sdl.moe.yabapi.data.login.SendSMSResponseCode
import sdl.moe.yabapi.util.logger

/**
 *  登录, 认证相关 API
 */
public object PassportApi : BiliApi {
    init {
        BiliClient.registerApi(this)
    }

    override val apiName: String
        get() = "passport"

    @Suppress("unused")
    public val BiliClient.passportApi: PassportApi
        get() = this@PassportApi

    public val callingCodeList: List<CallingCodeNode> = mutableListOf()

    /**
     * 获取人机验证码, 需要手动验证
     *
     * 可使用 [https://kuresaru.github.io/geetest-validator/]
     * @return [GetCaptchaResponse]
     */
    public suspend fun BiliClient.getCaptcha(): GetCaptchaResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Querying Login Captcha" }
        client.get<GetCaptchaResponse>(QUERY_CAPTCHA_URL).also {
            logger.debug { "Query Captcha Response: $it" }
        }
    }

    /**
     * 通过 Web 方式获取 RSA 公钥
     * @return [RsaGetResponse]
     */
    public suspend fun BiliClient.getRsaKeyWeb(): RsaGetResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Getting RSA Key by Web method" }
        client.get<RsaGetResponse>(RSA_GET_WEB_URL) {
            parameter("act", "getkey")
        }.also {
            logger.debug { "RSA Get Web Response: $it" }
        }
    }

    // /**
    //  * 通过 App 方式获取 RSA 公钥
    //  * @return [RsaGetResponse]
    //  */
    // public suspend fun BiliClient.getRsaKeyApp(
    //     appKey: String = APP_KEY,
    //     appSign: String = APP_SIGN,
    // ): RsaGetResponse = withContext(Platform.ioDispatcher) {
    //     logger.info { "Getting RSA Key by App method" }
    //     client.post<RsaGetResponse>(RSA_GET_APP_URL) {
    //         val param = Parameters.build {
    //             append("appkey", appKey)
    //             append("sign", appSign)
    //         }
    //         body = FormDataContent(param)
    //     }.also {
    //         logger.debug { "RSA Get App Response: $it" }
    //         if (it.code != RsaGetResponseCode.SUCCESS) {
    //             logger.warn { "RSA Get App Response Code is ${it.code}, with message: ${it.message}" }
    //         }
    //     }
    // }

    /**
     *  通过 Web 方式登录, 流程为 [getCaptcha] -> [getRsaKeyWeb] -> [loginWeb]
     *
     *  @param userName B站帐号, 手机号或邮箱
     *  @param pwdEncrypted 密码, 输入 rsa(pubkey, salt+password) 得到的密文
     *  @param validate 验证码平台返回
     *  @param seccode 验证码平台返回
     *  @param getCaptchaResponse B 站验证码请求回调
     */
    @Suppress("LongParameterList")
    public suspend fun BiliClient.loginWeb(
        userName: String,
        pwdEncrypted: String,
        validate: String,
        seccode: String,
        getCaptchaResponse: GetCaptchaResponse,
    ): LoginWebResponse = withContext(Platform.ioDispatcher) {
        noNeedLogin()
        logger.info { "Logging in by Web method" }
        client.post<LoginWebResponse>(LOGIN_WEB_URL) {
            val params = Parameters.build {
                append("captchaType", "6")
                append("username", userName)
                append("password", pwdEncrypted)
                append("keep", "true")
                append("key", getCaptchaResponse.data.result.loginKey)
                append("challenge", getCaptchaResponse.data.result.captchaKey)
                append("validate", validate)
                append("seccode", seccode)
            }
            body = FormDataContent(params)
        }.also {
            logger.debug { "Login Web Response: $it" }
            if (it.code != LoginWebResponseCode.SUCCESS) {
                logger.warn { "Login failed error code ${it.code}, with message: ${it.message}" }
            }
        }
    }

    /**
     * 命令行交互式帳號密碼登錄, 阻塞方法
     */
    // public suspend fun BiliClient.loginWebConsole(): Unit = withContext(Dispatchers.Default) {
    //     noNeedLogin()
    //     logger.info { "Starting Console Interactive Bilibili Web Login" }
    //     val userName = requireCmdInputString("Please Input Bilibili Username:")
    //     val pwd = requireCmdInputString("Please Input Bilibili Password:")
    //     val captchaResponse = getCaptcha()
    //     println("Please prove you are human, do the captcha via https://kuresaru.github.io/geetest-validator/ :")
    //     println("gt=${captchaResponse.data.result.id}, challenge=${captchaResponse.data.result.captchaKey}")
    //     val validate = requireCmdInputString("input the result, validate=")
    //     val seccode = "$validate|jordan"
    //     loginWeb(userName, pwd, validate, seccode, captchaResponse)
    // }

    public suspend fun BiliClient.getWebQRCode(): QRCodeWebGetResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Getting Web QRCode" }
        client.get<QRCodeWebGetResponse>(LOGIN_QRCODE_GET_WEB_URL).also {
            logger.debug { "QRCode Web Get Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "QRCode Web Get failed, error code: ${it.code}" }
        }
    }

    public suspend fun BiliClient.loginWebQRCode(
        qrResponse: QRCodeWebGetResponse,
    ): LoginWebQRCodeResponse = withContext(Platform.ioDispatcher) {
        noNeedLogin()
        logger.info { "Starting Logging in via Web QR Code" }
        client.post<LoginWebQRCodeResponse>(LOGIN_WEB_QRCODE_URL) {
            val params = Parameters.build {
                append("oauthKey", qrResponse.data.oauthKey)
            }
            body = FormDataContent(params)
        }.also {
            logger.debug { "Login Web QR Code Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "Login Web via QR Code failed, error code: ${it.code}" }
        }
    }

    /**
     * 交互式扫码登录封装
     */
    public suspend fun BiliClient.loginWebQRCodeInteractive(): LoginWebQRCodeResponse = withContext(Dispatchers.Default) {
        noNeedLogin()
        logger.debug { "Starting Interactive Login via Web QR Code" }
        val data = getWebQRCode()
        withContext(Dispatchers.Default) {
            println("打开网站，通过Bilibili手机客户端扫描二维码，在确认登录后回车继续。")
            println("https://qrcode.jp/qr?q=${data.data.url}&s=5")
        }
        readln()
        loginWebQRCode(data).also {
            logger.debug { "Login Web QR Code Response: $it" }
        }
    }

    /**
     * 获取国际区码
     * @return [CallingCodeGetResponse]
     */
    public suspend fun BiliClient.getCallingCode(): CallingCodeGetResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Getting Calling Code" }
        client.get<CallingCodeGetResponse>(GET_CALLING_CODE_URL).also {
            logger.debug { "Calling Code Get Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "Calling Code Get failed, error code: ${it.code}" }
        }
    }

    /**
     * 請求短信驗證碼
     * @param phone 手機號
     * @param cid 區域代碼 可通過 [getCallingCode] 获取
     * @param captchaResponse [GetCaptchaResponse]
     * @param validate 驗證碼結果
     * @param seccode 驗證碼結果
     */
    public suspend fun BiliClient.requestSMSCode(
        phone: Long,
        cid: Int,
        captchaResponse: GetCaptchaResponse,
        validate: String,
        seccode: String,
    ): SendSMSResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Requesting SMS Code" }
        client.post<SendSMSResponse>(SEND_SMS_URL) {
            val params = Parameters.build {
                append("tel", phone.toString())
                append("cid", cid.toString())
                append("token", captchaResponse.result.loginKey)
                append("challenge", captchaResponse.result.captchaKey)
                append("validate", validate)
                append("seccode", seccode)
            }
            params.formUrlEncode()
            body = FormDataContent(params)
        }.also {
            logger.debug { "SMS Code Request Response: $it" }
            if (it.code != SendSMSResponseCode.SUCCESS) {
                logger.warn { "SMS Code Request failed, error code: ${it.code}" }
            }
        }
    }

    /**
     * 通過短信驗證碼登錄, 流程爲 [getCaptcha] -> [requestSMSCode] -> [loginWebSMS]
     * @param phone 手機號
     * @param cid 區域代碼 可通過 [getCallingCode] 获取
     * @param code 短信驗證碼
     * @param sendSMSResponse [SendSMSResponse]
     */
    public suspend fun BiliClient.loginWebSMS(
        phone: Long,
        cid: Int,
        code: Int,
        sendSMSResponse: SendSMSResponse,
    ): LoginWebSMSResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Logging in via Web SMS" }
        client.post<LoginWebSMSResponse>(LOGIN_WEB_SMS_URL) {
            val smsCaptchaKey = sendSMSResponse.captchaKey ?: throw IllegalArgumentException("Captcha key is null")
            val params = Parameters.build {
                append("tel", phone.toString())
                append("cid", cid.toString())
                append("code", code.toString())
                append("captcha_key", smsCaptchaKey)
            }
            body = FormDataContent(params)
        }.also {
            logger.debug { "Login Web SMS Response: $it" }
            if (it.code != LoginWebSMSResponseCode.SUCCESS) logger.warn { "Login Web SMS failed, error code: ${it.code}" }
        }
    }

    // /**
    //  * 命令行交互式短信驗證登錄, 阻塞方法
    //  * @param needsCallingCode 是否需要國際區碼, 默認 +86
    //  */
    // public suspend fun BiliClient.loginWebSMSConsole(
    //     needsCallingCode: Boolean = false,
    // ): Unit = withContext(Dispatchers.Default) {
    //     noNeedLogin()
    //     logger.info { "Starting Console Interactive Bilibili Web Login" }
    //     var callingCode = 86
    //
    //     if (needsCallingCode) callingCode = requireCmdInputNumber("Please Input Calling Code (e.g. 86, 1):")
    //     val cid = async(Platform.ioDispatcher) {
    //         val cidList = getCallingCode().data.all
    //         cidList.first { it.callingCode == callingCode.toString() }.id.toInt()
    //     }
    //
    //     val phone: Long = requireCmdInputNumber("Please Input Phone Number (e.g. 13800138000):")
    //     val captchaResponse = getCaptcha()
    //
    //     fun sendSMS(): SendSMSResponse = withContext(Dispatchers.Default) {
    //         println("Please prove you are human, do the captcha via https://kuresaru.github.io/geetest-validator/ :")
    //         println("gt=${captchaResponse.data.result.id}, challenge=${captchaResponse.data.result.captchaKey}")
    //         val validate = requireCmdInputString("validate=")
    //         val sendSMSResponse = requestSMSCode(phone, cid.await(), captchaResponse, validate, "$validate|jordan")
    //         when (sendSMSResponse.code) {
    //             SendSMSResponseCode.SUCCESS -> sendSMSResponse
    //             else -> {
    //                 logger.warn { "SMS Code Request failed, error code: ${sendSMSResponse.code}, plz retry" }
    //                 sendSMS()
    //             }
    //         }
    //     }
    //
    //     val sendSMSResponse = async { sendSMS() }
    //     val code: Int = requireCmdInputNumber("Please Input SMS Code (e.g. 123456):")
    //     loginWebSMS(phone, cid.await(), code, sendSMSResponse.await())
    // }

    public suspend fun BiliClient.logOut(): LogOutResponse = withContext(Platform.ioDispatcher) {
        logger.info { "Logging out" }
        needLogin()
        client.post<LogOutResponse>(LOG_OUT_URL) {
            val params = Parameters.build {
                append("biliCSRF", getCsrfToken()?.value ?: "")
            }
            body = FormDataContent(params)
        }.also {
            logger.debug { "Log Out Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "Log Out failed, error code: ${it.code}" }
        }
    }
}
