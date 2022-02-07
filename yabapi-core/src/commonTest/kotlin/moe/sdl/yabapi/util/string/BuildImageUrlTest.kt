package moe.sdl.yabapi.util.string

import moe.sdl.yabapi.enums.ImageFormat.JPEG
import moe.sdl.yabapi.enums.ImageFormat.PNG
import kotlin.test.Test

internal class BuildImageUrlTest {
    @Test
    fun buildTest() {
        buildImageUrl("https://i2.hdslb.com/bfs/archive/4a4bf858fc158668bf81c7e206799a5f6f99d6c3.png", PNG, 75, 800, 600).also(::println)
        buildImageUrl("https://i1.hdslb.com/bfs/archive/60be8bba6e9648ff29e008752ed0fecc45c55714.png@672w_378h_1c.webp", JPEG, 80, 1000, 500).also(::println)
    }
}
