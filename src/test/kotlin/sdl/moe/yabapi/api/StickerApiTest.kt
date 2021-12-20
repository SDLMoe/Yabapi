// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.StickerApi.getAllStickers
import sdl.moe.yabapi.data.sticker.StickerType.EMOTICON
import sdl.moe.yabapi.enums.StickerBusiness
import sdl.moe.yabapi.storage.FileCookieStorage
import java.io.File

internal class StickerApiTest {

    @Test
    suspend fun getAllStickers() {
        val client = BiliClient(cookieStorage = FileCookieStorage("./cookies.json"))
        val root = File("./tmp/stickers")
        val allFile = File("${root.path}/all.txt")
        root.mkdirs()
        client.getAllStickers(StickerBusiness.DYNAMIC).data?.all?.forEach { set ->
            val setFile = File("${root.path}/${set.name}.txt")
            set.stickerList.filter { it.type != EMOTICON }.forEach { data ->
                data.url?.let {
                    try {
                        val text = data.text?.replace(Regex("""[\[\]]"""), "")
                        val str = """
                        $it 
                            dir=./out/${set.name}
                            out=$text.png 
                    """.trimIndent()
                        setFile.appendText(str + "\n")
                        allFile.appendText(str + "\n")
                    } catch (e: Exception) {
                        // DO NOTHING
                    }
                }
            }
        }
    }
}
