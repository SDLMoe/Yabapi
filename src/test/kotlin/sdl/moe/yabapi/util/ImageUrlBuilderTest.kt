package sdl.moe.yabapi.util

import org.junit.jupiter.api.Test
import sdl.moe.yabapi.enums.ImageFormat

class ImageUrlBuilderTest {
    @Test
    fun main() {
        val url = "https://i1.hdslb.com/bfs/archive/e5fff1472bad1c0c6bcb3004205f9be23b58ffc0.jpg@1500w_1500h_75q.webp"
        buildImageUrl(url, ImageFormat.JPEG).also(::println)
        buildImageUrl(url, ImageFormat.JPEG, 75, 114, 514).also(::println)
        buildImageUrl(url, ImageFormat.WEBP, 75, 114, 514).also(::println)
    }
}
