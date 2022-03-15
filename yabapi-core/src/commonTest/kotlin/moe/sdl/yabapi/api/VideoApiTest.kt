package moe.sdl.yabapi.api

import moe.sdl.yabapi.Yabapi.yabapiLogLevel
import moe.sdl.yabapi.client
import moe.sdl.yabapi.data.stream.QnQuality.V8K
import moe.sdl.yabapi.data.stream.StreamRequest
import moe.sdl.yabapi.data.stream.VideoFnvalFormat
import moe.sdl.yabapi.data.video.encodeToSrt
import moe.sdl.yabapi.enums.LogLevel
import moe.sdl.yabapi.enums.video.CollectAction.ADD
import moe.sdl.yabapi.enums.video.CollectAction.REMOVE
import moe.sdl.yabapi.enums.video.LikeAction.LIKE
import moe.sdl.yabapi.enums.video.LikeAction.UNLIKE
import moe.sdl.yabapi.enums.video.VideoFormat.DASH
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import moe.sdl.yabapi.util.encoding.avInt
import moe.sdl.yabapi.util.encoding.bv
import kotlin.test.Test

internal class VideoApiTest {

    init {
        initTest()
    }

    @Test
    fun getVideoInfo(): Unit = runTest {
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

    @Test
    fun getUgcSeasonInfo(): Unit = runTest {
        listOf("BV1ei4y1X7mo", "BV1jF411B7sw", "BV17A411575p", "BV1h34y1o7bz").forEach {
            client.getVideoInfo(it)
        }
    }

    @Test
    fun getVideoPlayerInfoTest(): Unit = runTest {
        client.apply {
            val aid = "BV1MJ411C7ie".avInt
            val cid = getVideoParts(aid).data.first().part!!
            getVideoPlayerInfo(aid, cid)
        }
    }

    @Test
    fun subtitleGetTest(): Unit = runTest {
        yabapiLogLevel.getAndSet(LogLevel.VERBOSE)
        val aid = 60977932
        client.getVideoParts(aid).data.asSequence().map { it.cid }.forEach { cid ->
            val list = client.getVideoInfo(aid, cid).data?.subtitle?.list.orEmpty()
            val track = list.firstOrNull() ?: return@forEach
            val content = client.getSubtitleContent(track.subtitleUrl ?: return@forEach)
            content.body.filterIndexed { index, _ -> index in 50..100 }.encodeToSrt().also(::println)
        }
    }

    @Test
    fun getVideoParts(): Unit = runTest {
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

    @Test
    fun getVideoDescription(): Unit = runTest {
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

    @Test
    fun likeVideoTest(): Unit = runTest {
        client.likeVideo("BV13Z4y1F798", LIKE)
        client.likeVideo("BV13Z4y1F798", UNLIKE)
    }

    @Test
    fun checkVideoLikeTest(): Unit = runTest {
        client.checkVideoLike("BV13Z4y1F798")
    }

    @Test
    fun coinVideoTest(): Unit = runTest {
        client.coinVideo("BV13Z4y1F798")
        client.coinVideo("BV13Z4y1F798", withLike = true)
    }

    @Test
    fun checkCoinTest(): Unit = runTest {
        client.checkVideoCoin("BV13Z4y1F798")
    }

    @Test
    fun collectVideo(): Unit = runTest {
        client.collectVideo("BV13Z4y1F798", ADD, listOf(83867716))
        client.collectVideo("BV13Z4y1F798", REMOVE, listOf(83867716))
    }

    @Test
    fun checkVideoCollect(): Unit = runTest {
        client.checkVideoCollect("BV13Z4y1F798")
    }

    @Test
    fun comboLikeTest(): Unit = runTest {
        client.comboLike(170001)
    }

    @Test
    fun shareTest(): Unit = runTest {
        client.shareVideo("BV13Z4y1F798")
    }

    @Test
    fun fetchVideoStreamTest(): Unit = runTest {
        // client.loginWebQRCodeInteractive()
        val bv = "av419059870".bv
        val data = client.getVideoParts(bv).data
        client.fetchVideoStream(
            bv,
            data[0].cid!!,
            StreamRequest(
                qnQuality = V8K,
                fnvalFormat = VideoFnvalFormat(
                    format = DASH,
                    needHDR = true,
                    need4K = true,
                    need8K = true,
                    needDolby = true
                )
            )
        )
    }

    @Test
    fun fetchPgcStreamTest(): Unit = runTest {
        client.fetchPgcStream(457775)
    }

    @Test
    fun getTimelineHotTest(): Unit = runTest {
        val cid = client.getVideoParts("BV1qM4y1w716").data[0].cid
        client.getTimelineHot(cid!!)
    }

    @Test
    fun getVideoOnlineTest(): Unit = runTest {
        val cid = client.getVideoParts("BV1mM4y1F7yh").data[0].cid
        client.getVideoOnline("BV1mM4y1F7yh", cid!!)
    }

    @Test
    fun getVideoTagsTest(): Unit = runTest {
        client.getVideoTags("BV1mM4y1F7yh")
    }

    @Test
    fun getVideoRelatedTest(): Unit = runTest {
        client.getVideoRelated("BV1jF411B7sw")
    }

    @Test
    fun reportVideoProgressTest(): Unit = runTest {
        val avid = 170001
        val cid = client.getVideoParts(avid).data[0].cid
        client.reportVideoProgress(avid, cid!!, 3 * 60)
    }

    @Test
    fun getInteractiveInfoTest(): Unit = runTest {
        val bvid = "BV1Xb4y1x7Rd"
        with(client) {
            val graph = getVideoPlayerInfo(bvid, getVideoParts(bvid).data.first().cid!!).data!!.interactionInfo!!.graphVersion!!
            getInteractiveInfo(bvid, graph)
        }
    }
}
