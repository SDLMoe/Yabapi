// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

private const val ENCRYPT_MAX_SIZE = 117

internal fun String.getPublicKey(): PublicKey {
    val trimmed = this
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replace("\n", "")
    val kf = KeyFactory.getInstance("RSA")
    return kf.generatePublic(X509EncodedKeySpec(Base64.decode(trimmed)))
}

internal fun rsaEncryptByPublicKey(input: String, publicKey: PublicKey): String {
    val byteArray = input.toByteArray()
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    cipher.init(Cipher.ENCRYPT_MODE, publicKey)

    var temp: ByteArray
    var offset = 0
    val bos = ByteArrayOutputStream()

    while (byteArray.size - offset > 0) {
        if (byteArray.size - offset >= ENCRYPT_MAX_SIZE) {
            temp = cipher.doFinal(byteArray, offset, ENCRYPT_MAX_SIZE)
            offset += ENCRYPT_MAX_SIZE
        } else {
            temp = cipher.doFinal(byteArray, offset, byteArray.size - offset)
            offset = byteArray.size
        }
        bos.write(temp)
    }
    bos.close()

    return Base64.encode(bos.toByteArray())
}

/** md5(salt+data) */
internal fun rsaEncryptWithSalt(data: String, publicKey: String, salt: String): String =
    rsaEncryptByPublicKey(salt + data, publicKey.getPublicKey())
