// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.data.stream.QnQuality.V8K
import sdl.moe.yabapi.data.stream.StreamRequest
import sdl.moe.yabapi.data.stream.VideoFnvalFormat
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.enums.video.CollectAction.ADD
import sdl.moe.yabapi.enums.video.CollectAction.REMOVE
import sdl.moe.yabapi.enums.video.LikeAction.LIKE
import sdl.moe.yabapi.enums.video.LikeAction.UNLIKE
import sdl.moe.yabapi.enums.video.VideoFormat.DASH
import sdl.moe.yabapi.runTest
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.test.Test

internal class VideoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    @Test
    fun getVideoInfo() {
        runTest {
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
        runTest {
            listOf("BV1ei4y1X7mo", "BV1jF411B7sw", "BV17A411575p", "BV1h34y1o7bz").forEach {
                client.getVideoInfo(it)
            }
        }
    }

    @Test
    fun getVideoParts() {
        runTest {
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
        runTest {
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
        runTest {
            client.likeVideo("BV13Z4y1F798", LIKE)
            client.likeVideo("BV13Z4y1F798", UNLIKE)
        }
    }

    @Test
    fun checkVideoLikeTest() {
        runTest {
            client.checkVideoLike("BV13Z4y1F798")
        }
    }

    @Test
    fun coinVideoTest() {
        runTest {
            client.coinVideo("BV13Z4y1F798")
            client.coinVideo("BV13Z4y1F798", withLike = true)
        }
    }

    @Test
    fun checkCoinTest() {
        runTest {
            client.checkVideoCoin("BV13Z4y1F798")
        }
    }

    @Test
    fun collectVideo() {
        runTest {
            client.collectVideo("BV13Z4y1F798", ADD, listOf(83867716))
            client.collectVideo("BV13Z4y1F798", REMOVE, listOf(83867716))
        }
    }

    @Test
    fun checkVideoCollect() {
        runTest {
            client.checkVideoCollect("BV13Z4y1F798")
        }
    }

    @Test
    fun comboLikeTest() {
        runTest {
            client.comboLike(170001)
        }
    }

    @Test
    fun shareTest() {
        runTest {
            client.shareVideo("BV13Z4y1F798")
        }
    }

    @Test
    fun fetchVideoStreamTest() {
        runTest {
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
        runTest {
            val cid = client.getVideoParts("BV1qM4y1w716").data[0].cid
            client.getTimelineHot(cid)
        }
    }

    @Test
    fun getVideoOnlineTest() {
        runTest {
            val cid = client.getVideoParts("BV1mM4y1F7yh").data[0].cid
            client.getVideoOnline("BV1mM4y1F7yh", cid)
        }
    }

    @Test
    fun getVideoTagsTest() {
        runTest {
            client.getVideoTags("BV1mM4y1F7yh")
        }
    }

    @Test
    fun getVideoRelatedTest() {
        runTest {
            client.getVideoRelated("BV1jF411B7sw")
        }
    }
}
