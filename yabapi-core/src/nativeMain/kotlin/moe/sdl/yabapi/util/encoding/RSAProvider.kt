package moe.sdl.yabapi.util.encoding

/**
 * Do not provide RSA in Kotlin/Native
 *
 * Too complex, pr is welcomed.
 *
 * Just login with cookie
 */
internal actual object RSAProvider : RSA {
    override fun encryptWithPublicKey(publicKey: String, data: String): String = ""
    override fun decryptWithPrivateKey(privateKey: String, data: String): String = ""
}
