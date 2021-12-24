// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import io.matthewnelson.component.base64.Base64
import io.matthewnelson.component.base64.decodeBase64ToArray
import io.matthewnelson.component.base64.encodeBase64
import io.matthewnelson.component.base64.encodeBase64ToByteArray
import io.matthewnelson.component.base64.encodeBase64ToCharArray

public object Base64 {

    private val base64: Base64 = Base64.Default
    // private val base64: Base64 = Base64.UrlSafe(pad = true)

    public fun base64Encode(bytes: ByteArray): String =
        bytes.encodeBase64(base64 = base64)

    public fun base64EncodeToCharArray(bytes: ByteArray): CharArray =
        bytes.encodeBase64ToCharArray(base64 = base64)

    public fun base64EncodeToByteArray(bytes: ByteArray): ByteArray =
        bytes.encodeBase64ToByteArray(base64 = base64)

    public fun base64Decode(string: String): ByteArray? =
        string.decodeBase64ToArray()

    public fun base64Decode(chars: CharArray): ByteArray? =
        chars.decodeBase64ToArray()
}
