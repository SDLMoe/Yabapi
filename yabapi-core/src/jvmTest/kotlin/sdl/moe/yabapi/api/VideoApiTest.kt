// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.VideoApi.checkVideoCoin
import sdl.moe.yabapi.api.VideoApi.checkVideoCollect
import sdl.moe.yabapi.api.VideoApi.checkVideoLike
import sdl.moe.yabapi.api.VideoApi.coinVideo
import sdl.moe.yabapi.api.VideoApi.collectVideo
import sdl.moe.yabapi.api.VideoApi.comboLike
import sdl.moe.yabapi.api.VideoApi.fetchVideoStream
import sdl.moe.yabapi.api.VideoApi.getTimelineHot
import sdl.moe.yabapi.api.VideoApi.getVideoDescription
import sdl.moe.yabapi.api.VideoApi.getVideoInfo
import sdl.moe.yabapi.api.VideoApi.getVideoOnline
import sdl.moe.yabapi.api.VideoApi.getVideoParts
import sdl.moe.yabapi.api.VideoApi.getVideoRelated
import sdl.moe.yabapi.api.VideoApi.getVideoTags
import sdl.moe.yabapi.api.VideoApi.likeVideo
import sdl.moe.yabapi.api.VideoApi.shareVideo
import sdl.moe.yabapi.data.stream.QnQuality.V8K
import sdl.moe.yabapi.data.stream.StreamRequest
import sdl.moe.yabapi.data.stream.VideoFnvalFormat
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.enums.video.CollectAction.ADD
import sdl.moe.yabapi.enums.video.CollectAction.REMOVE
import sdl.moe.yabapi.enums.video.LikeAction.LIKE
import sdl.moe.yabapi.enums.video.LikeAction.UNLIKE
import sdl.moe.yabapi.enums.video.VideoFormat.DASH
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.test.Test

internal class VideoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    private val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

    @Test
    fun getVideoInfo() {
        runBlocking {
            listOf(
                2,
                7,
                507448290,
                933731156,
                971149764,
                422651576,
                974531866,
                892816331,
                252043983,
                252702924,
                206735865,
                549481623,
            ).forEach {
                client.getVideoInfo(it)
            }
        }
    }

    @Test
    fun getUgcSeasonInfo() {
        runBlocking {
            listOf("BV1ei4y1X7mo", "BV1jF411B7sw", "BV17A411575p", "BV1h34y1o7bz").forEach {
                client.getVideoInfo(it)
            }
        }
    }

    @Test
    fun getVideoParts() {
        runBlocking {
            listOf(
                507448290,
                933731156,
                971149764,
                422651576,
                974531866,
                892816331,
                252043983,
                252702924,
                206735865,
                549481623,
            ).forEach {
                client.getVideoParts(it)
            }
        }
    }

    @Test
    fun getVideoDescription() {
        runBlocking {
            listOf(
                507448290,
                933731156,
                971149764,
                422651576,
                974531866,
                892816331,
                252043983,
                252702924,
                206735865,
                549481623,
            ).forEach {
                client.getVideoDescription(it)
            }
        }
    }

    @Test
    fun likeVideoTest() {
        runBlocking {
            client.likeVideo("BV13Z4y1F798", LIKE)
            client.likeVideo("BV13Z4y1F798", UNLIKE)
        }
    }

    @Test
    fun checkVideoLikeTest() {
        runBlocking {
            client.checkVideoLike("BV13Z4y1F798")
        }
    }

    @Test
    fun coinVideoTest() {
        runBlocking {
            client.coinVideo("BV13Z4y1F798")
            client.coinVideo("BV13Z4y1F798", withLike = true)
        }
    }

    @Test
    fun checkCoinTest() {
        runBlocking {
            client.checkVideoCoin("BV13Z4y1F798")
        }
    }

    @Test
    fun collectVideo() {
        runBlocking {
            client.collectVideo("BV13Z4y1F798", ADD, listOf(83867716))
            client.collectVideo("BV13Z4y1F798", REMOVE, listOf(83867716))
        }
    }

    @Test
    fun checkVideoCollect() {
        runBlocking {
            client.checkVideoCollect("BV13Z4y1F798")
        }
    }

    @Test
    fun comboLikeTest() {
        runBlocking {
            client.comboLike(170001)
        }
    }

    @Test
    fun shareTest() {
        runBlocking {
            client.shareVideo("BV13Z4y1F798")
        }
    }

    @Test
    fun fetchVideoStreamTest() {
        runBlocking {
            val bv = "BV1qM4y1w716"
            val data = client.getVideoParts(bv).data
            client.fetchVideoStream(
                bv,
                data[0].cid,
                StreamRequest(
                    qnQuality = V8K,
                    fnvalFormat = VideoFnvalFormat(
                        format = DASH,
                        needHDR = true,
                        need4K = true,
                        need8K = true,
                        needDolby = true)
                )
            )
        }
    }

    @Test
    fun getTimelineHotTest() {
        runBlocking {
            val cid = client.getVideoParts("BV1qM4y1w716").data[0].cid
            client.getTimelineHot(cid)
        }
    }

    @Test
    fun getVideoOnlineTest() {
        runBlocking {
            val cid = client.getVideoParts("BV1mM4y1F7yh").data[0].cid
            client.getVideoOnline("BV1mM4y1F7yh", cid)
        }
    }

    @Test
    fun getVideoTagsTest() {
        runBlocking {
            client.getVideoTags("BV1mM4y1F7yh")
        }
    }

    @Test
    fun getVideoRelatedTest() {
        runBlocking {
            client.getVideoRelated("BV1jF411B7sw")
        }
    }
}
