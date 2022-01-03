// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

internal interface RSA {
    fun encryptWithPublicKey(publicKey: String, data: String): String
    fun decryptWithPrivateKey(privateKey: String, data: String): String
}

internal expect object RSAProvider : RSA

internal fun trimPem(string: String): String = Regex("""([\s\r\n]|(-.*-))""").replace(string, "")
