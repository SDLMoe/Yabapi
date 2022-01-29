package moe.sdl.yabapi.util.encoding

internal interface RSA {
    fun encryptWithPublicKey(publicKey: String, data: String): String
    fun decryptWithPrivateKey(privateKey: String, data: String): String
}

internal expect object RSAProvider : RSA

internal fun trimPem(string: String): String = Regex("""([\s\r\n]|(-.*-))""").replace(string, "")
