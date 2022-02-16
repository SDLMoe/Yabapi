package moe.sdl.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.asFlow
import moe.sdl.yabapi.client
import moe.sdl.yabapi.enums.StickerBusiness.REPLY
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import org.junit.jupiter.api.Test
import java.io.File

internal class StickerApiTest {
    init {
        initTest()
    }

    @Test
    fun getAllSticker() = runTest {
        client.getAllStickers(REPLY).data!!.all
            .first { it.name!!.contains("小黄脸") }
            .stickerList.asSequence()
            .map { it.text!!.substringAfter('[').substringBefore(']') to it.url }
            .asFlow().collect { (name, url) ->
                val byteArray = async { client.client.get<ByteArray>(url!!) }
                val file = File("./tmp/小黄脸/$name.png").apply {
                    parentFile?.mkdirs()
                    createNewFile()
                    writeBytes(byteArray.await())
                }
            }
    }
}
