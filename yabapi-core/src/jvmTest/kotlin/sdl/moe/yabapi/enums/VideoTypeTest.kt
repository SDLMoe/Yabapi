// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.enums

import kotlin.test.Test

internal class VideoTypeTest {
    @Test
    fun getAllTypesTest() {
        VideoType.getAllTypes()
        val fromCode = VideoType.fromCode("douga")
        val fromTid = VideoType.fromTid(1)
        assert(fromCode === Douga)
        assert(fromTid === Douga)
    }

    @Test
    fun getAllRouteUrlTest() {
        VideoType.getAllUrl().forEach(::println)
    }
}