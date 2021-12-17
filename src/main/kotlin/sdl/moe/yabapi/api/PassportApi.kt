package sdl.moe.yabapi.api

import io.ktor.client.features.cookies.cookies
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import net.glxn.qrgen.core.image.ImageType
import net.glxn.qrgen.javase.QRCode
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.PassportApi.getRsaKeyWeb
import sdl.moe.yabapi.api.PassportApi.loginWeb
import sdl.moe.yabapi.api.PassportApi.loginWebConsole
import sdl.moe.yabapi.api.PassportApi.queryLoginCaptcha
import sdl.moe.yabapi.consts.APP_KEY
import sdl.moe.yabapi.consts.APP_SIGN
import sdl.moe.yabapi.consts.passport.GET_CALLING_CODE_URL
import sdl.moe.yabapi.consts.passport.LOGIN_QRCODE_GET_WEB_URL
import sdl.moe.yabapi.consts.passport.LOGIN_WEB_QRCODE_URL
import sdl.moe.yabapi.consts.passport.LOGIN_WEB_URL
import sdl.moe.yabapi.consts.passport.QUERY_CAPTCHA_URL
import sdl.moe.yabapi.consts.passport.RSA_GET_APP_URL
import sdl.moe.yabapi.consts.passport.RSA_GET_WEB_URL
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.login.CallingCodeGetResponse
import sdl.moe.yabapi.data.login.LoginWebQRCodeResponse
import sdl.moe.yabapi.data.login.LoginWebResponse
import sdl.moe.yabapi.data.login.LoginWebResponseCode.SUCCESS
import sdl.moe.yabapi.data.login.QRCodeWebGetResponse
import sdl.moe.yabapi.data.login.QueryCaptchaResponse
import sdl.moe.yabapi.data.login.RsaGetResponse
import sdl.moe.yabapi.data.login.RsaGetResponseCode.SIGN_INVALID
import sdl.moe.yabapi.data.login.RsaGetResponseCode.UNKNOWN
import sdl.moe.yabapi.util.rsaEncryptWithSalt
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JDialog
import javax.swing.JLabel
import javax.swing.JPanel

private val logger = KotlinLogging.logger {}

@Suppress("TooManyFunctions")
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
     * 获取人机验证码, 需要手动验证
     *
     * 可使用 [https://kuresaru.github.io/geetest-validator/]
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
    public suspend inline fun queryLoginCaptcha(client: BiliClient): QueryCaptchaResponse = client.queryLoginCaptcha()

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
    public suspend inline fun getRsaKeyWeb(client: BiliClient): RsaGetResponse = client.getRsaKeyWeb()

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
    public suspend inline fun getRsaKeyApp(client: BiliClient): RsaGetResponse = client.getRsaKeyApp()

    /**
     *  通过 Web 方式登录, 流程为 [queryLoginCaptcha] -> [getRsaKeyWeb] -> [loginWeb]
     *
     *  可以通过 [loginWebConsole] 交互式登录(仅命令行)
     *  @param userName B站帐号, 手机号或邮箱
     *  @param password 密码, 输入明文
     *  @param validate 验证码平台返回
     *  @param seccode 验证码平台返回
     *  @param queryCaptchaResponse B 站验证码请求回调
     *  @param rsaGetResponse B 站 RSA 公钥请求回调
     */
    @Suppress("LongParameterList")
    @JvmName("loginWebExt")
    public suspend fun BiliClient.loginWeb(
        userName: String,
        password: String,
        validate: String,
        seccode: String,
        queryCaptchaResponse: QueryCaptchaResponse,
        rsaGetResponse: RsaGetResponse? = null,
    ): LoginWebResponse = withContext(Dispatchers.IO) {
        logger.info { "Logging in by Web method" }
        val actualRsaData = rsaGetResponse ?: getRsaKeyWeb()
        require(actualRsaData.rsa != null) { "RSA Key is null, check response data: $rsaGetResponse" }
        require(actualRsaData.salt != null) { "Salt is null, check response data: $rsaGetResponse" }
        val pwdEncrypted = rsaEncryptWithSalt(data = password, salt = actualRsaData.salt, publicKey = actualRsaData.rsa)
        client.post<LoginWebResponse>(LOGIN_WEB_URL) {
            val params = Parameters.build {
                append("captchaType", "6")
                append("username", userName)
                append("password", pwdEncrypted)
                append("keep", "true")
                append("key", queryCaptchaResponse.data.result.loginKey)
                append("challenge", queryCaptchaResponse.data.result.captchaKey)
                append("validate", validate)
                append("seccode", seccode)
            }
            body = FormDataContent(params)
        }.also {
            logger.debug { "Login Web Response: $it" }
            if (it.code != SUCCESS) {
                logger.warn { "Login failed error code ${it.code}, with message: ${it.message}" }
            }
        }
    }

    @Suppress("LongParameterList")
    @JvmName("loginWeb")
    public suspend inline fun loginWeb(
        client: BiliClient,
        userName: String,
        password: String,
        validate: String,
        seccode: String,
        queryCaptchaResponse: QueryCaptchaResponse,
        rsaGetResponse: RsaGetResponse,
    ): LoginWebResponse = client.loginWeb(userName, password, validate, seccode, queryCaptchaResponse, rsaGetResponse)

    @JvmName("loginWebConsoleExt")
    public fun BiliClient.loginWebConsole(): Unit = runBlocking {
        val bclient = this@loginWebConsole
        logger.info { "Starting Console Interactive Bilibili Web Login" }
        logger.info { "Please Input Bilibili Username:" }
        val userName = readlnOrNull().toString()
        logger.info { "Please Input Bilibili Password:" }
        val pwd = readlnOrNull().toString()
        val captchaResponse = bclient.queryLoginCaptcha()
        logger.info { "Please prove you are human, do the captcha via https://kuresaru.github.io/geetest-validator/ :" }
        logger.info {
            "gt=${captchaResponse.data.result.id}, challenge=${captchaResponse.data.result.captchaKey}"
        }
        logger.info { "input the result, validate=" }
        val validate = readlnOrNull().toString()
        val seccode = "$validate|jordan"
        bclient.loginWeb(userName, pwd, validate, seccode, captchaResponse)
    }

    @Suppress("NOTHING_TO_INLINE")
    @JvmName("loginWebConsole")
    public inline fun loginWebConsole(client: BiliClient): Unit = client.loginWebConsole()

    @JvmName("getWebQRCodeExt")
    public suspend fun BiliClient.getWebQRCode(): QRCodeWebGetResponse = withContext(Dispatchers.IO) {
        logger.info { "Getting Web QRCode" }
        client.get<QRCodeWebGetResponse>(LOGIN_QRCODE_GET_WEB_URL).also {
            logger.debug { "QRCode Web Get Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "QRCode Web Get failed, error code: ${it.code}" }
        }
    }

    @JvmName("getWebQRCode")
    public suspend inline fun getWebQRCode(client: BiliClient): QRCodeWebGetResponse = client.getWebQRCode()

    @JvmName("loginWebQRCodeExt")
    public suspend fun BiliClient.loginWebQRCode(
        qrResponse: QRCodeWebGetResponse
    ): LoginWebQRCodeResponse = withContext(Dispatchers.IO) {
        logger.info { "Starting Logging in via Web QR Code" }
        client.post<LoginWebQRCodeResponse>(LOGIN_WEB_QRCODE_URL) {
            val params = Parameters.build {
                append("oauthKey", qrResponse.data.oauthKey)
            }
            body = FormDataContent(params)
        }.also {
            logger.debug { "Login Web QR Code Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "Login Web QR Code failed, error code: ${it.code}" }
        }
    }

    @JvmName("loginWebQRCode")
    public suspend inline fun loginWebQRCode(client: BiliClient): LoginWebQRCodeResponse =
        client.loginWebQRCode(getWebQRCode(client))

    private suspend fun showQRCode(string: String) = withContext(Dispatchers.Default) {
        @Suppress("MagicNumber") val qrcodeSize = 500
        val img =
            async { QRCode.from(string).to(ImageType.PNG).withSize(qrcodeSize, qrcodeSize).stream().toByteArray() }
        val dialog = object : JDialog() {
            private val contentPane: JPanel = JPanel()

            init {
                this.title = "使用 APP 扫码, 同意后关闭此窗口"
                size = Dimension(qrcodeSize, qrcodeSize)
                setContentPane(contentPane)
                val imgGet = runBlocking { img.await() }
                contentPane.add(JLabel(ImageIcon(imgGet)))
                setLocationRelativeTo(null)
                isModal = true
                this.defaultCloseOperation = DISPOSE_ON_CLOSE
            }
        }
        dialog.pack()
        dialog.isVisible = true
    }

    /**
     * 阻塞方法, 交互式扫码登录封装
     */
    @JvmName("loginWebQRCodeInteractiveExt")
    public fun BiliClient.loginWebQRCodeInteractive(): LoginWebQRCodeResponse = runBlocking {
        logger.debug { "Starting Interactive Login via Web QR Code" }
        val bclient = this@loginWebQRCodeInteractive
        val data = bclient.getWebQRCode()
        showQRCode(data.data.url)

        bclient.client.cookies("https://bilibili.com").also {
            logger.debug { "Cookies: $it" }
        }
        bclient.loginWebQRCode(data).also {
            logger.debug { "Login Web QR Code Response: $it" }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    @JvmName("loginWebQRCodeInteractive")
    public inline fun loginWebQRCodeInteractive(client: BiliClient): LoginWebQRCodeResponse =
        client.loginWebQRCodeInteractive()

    /**
     * 获取国际区码
     * @return [CallingCodeGetResponse]
     */
    @JvmName("getCallingCodeExt")
    public suspend fun BiliClient.getCallingCode(): CallingCodeGetResponse = withContext(Dispatchers.IO) {
        logger.info { "Getting Calling Code" }
        client.get<CallingCodeGetResponse>(GET_CALLING_CODE_URL).also {
            logger.debug { "Calling Code Get Response: $it" }
            if (it.code != GeneralCode.SUCCESS) logger.warn { "Calling Code Get failed, error code: ${it.code}" }
        }
    }

    @JvmName("getCallingCode")
    public suspend inline fun getCallingCode(client: BiliClient): CallingCodeGetResponse = client.getCallingCode()
}
