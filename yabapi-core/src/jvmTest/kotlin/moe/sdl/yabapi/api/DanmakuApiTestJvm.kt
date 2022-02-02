package moe.sdl.yabapi.api

import moe.sdl.yabapi.initTest

internal class DanmakuApiTestJvm {
    init {
        initTest()
    }

    // @Test
    // fun getDanmakuMetadataTest() = runTest {
    //     val aid = "BV13r4y1Y7VU".avInt
    //     val cid = client.getVideoParts(aid).data[0].cid
    //     client.getDanmakuMetadata(cid!!).also {
    //         println(it.toString())
    //     }
    // }
}
