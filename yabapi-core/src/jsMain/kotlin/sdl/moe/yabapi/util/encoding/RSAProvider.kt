// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import JSEncrypt

internal actual object RSAProvider : RSA {

    private val provider = JSEncrypt()

    override fun encryptWithPublicKey(publicKey: String, data: String): String {
        provider.setPublicKey(publicKey)
        return provider.encrypt(data) as String
    }

    override fun decryptWithPrivateKey(privateKey: String, data: String): String {
        provider.setPrivateKey(privateKey)
        return provider.decrypt(data) as String
    }
}
