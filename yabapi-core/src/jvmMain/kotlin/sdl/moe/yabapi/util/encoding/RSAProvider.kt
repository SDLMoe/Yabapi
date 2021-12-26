// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

internal actual object RSAProvider : RSA {

    private val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")

    private val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding") // Do not need secure mode, for external use only

    private fun getPublicKey(string: String): PublicKey {
        return keyFactory.generatePublic(X509EncodedKeySpec(Base64().base64Decode(string)))
    }

    private fun getPrivateKey(string: String): PrivateKey {
        return keyFactory.generatePrivate(PKCS8EncodedKeySpec(Base64().base64Decode(string)))
    }

    override fun encryptWithPublicKey(publicKey: String, data: String): String {
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey))
        val final = cipher.doFinal(data.toByteArray())
        return Base64().base64Encode(final)
    }

    override fun decryptWithPrivateKey(privateKey: String, data: String): String {
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey))
        val final = cipher.doFinal(Base64().base64Decode(data))
        return String(final)
    }
}
