// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import org.junit.jupiter.api.Test

internal class Base64Test {

    @Test
    fun encode() {
        val input = "Hello World!"
        val output = Base64.base64Encode(input.toByteArray())
        assert(output == "SGVsbG8gV29ybGQh")
    }

    fun decode() {
        val input = "SGVsbG8gV29ybGQh"
        val output = Base64.base64Decode(input)
        assert(output.contentEquals("Hello World!".toByteArray()))
    }
}
