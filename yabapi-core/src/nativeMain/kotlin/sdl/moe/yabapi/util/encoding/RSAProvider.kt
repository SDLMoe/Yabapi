// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

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
