package moe.sdl.yabapi.api

import com.soywiz.korio.async.launch
import moe.sdl.yabapi.client
import moe.sdl.yabapi.enums.video.Kichiku
import moe.sdl.yabapi.enums.video.VideoSort.COLLECT
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import moe.sdl.yabapi.util.Logger
import kotlin.native.concurrent.SharedImmutable
import kotlin.test.Test

@SharedImmutable
private val logger by lazy { Logger("InfoApiTest") }

internal class InfoApiTest {

    init {
        initTest()
    }

    @Test
    fun getBasicInfoTest() {
        runTest {
            // client.loginWebQRCodeInteractive()
            client.getBasicInfo()
        }
    }

    @Test
    fun getStatTest() {
        runTest {
            client.getStat()
        }
    }

    @Test
    fun getCoinTest() {
        runTest {
            client.getCoinInfo()
        }
    }

    @Test
    fun getAccountInfoTest() {
        runTest {
            client.getAccountInfo()
        }
    }

    @Test
    fun getExpTest() {
        runTest {
            val expData = client.getExpReward().data
            val coinExpData = client.getCoinExp()
            expData?.replaceWithCoinExp(coinExpData)?.countTodayRewarded().also {
                logger.info { "Sum: $it" }
            }
        }
    }

    @Test
    fun getVipStatTest() {
        runTest {
            client.getVipStat()
        }
    }

    @Test
    fun getSecureInfoTest() {
        runTest {
            client.getSecureInfo()
        }
    }

    @Test
    fun getRealNameInfoTest() {
        runTest {
            client.getRealNameInfo()
        }
    }

    @Test
    fun getRealNameDetailedTest() {
        runTest {
            client.getRealNameDetailed()
        }
    }

    @Test
    fun getCoinLogTest() {
        runTest {
            client.getCoinLog()
        }
    }

    @Test
    fun getUserSpaceTest() {
        runTest {
            listOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
                .forEach {
                    client.getUserSpace(it)
                }
        }
    }

    @Test
    fun getUserCardTest() {
        runTest {
            val random = listOf(true, false)
            // val body: String = client.client.get(USER_CARD_GET_URL) {
            //     parameter("mid", "2")
            //     parameter("photo", "true")
            // }
            // println(body)
            listOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
                .forEach {
                    client.getUserCard(it, random.random())
                }
        }
    }

    @Test
    fun getPinnedVideoTest() = runTest {
        client.getPinnedVideo(client.getBasicInfo().data.mid!!)
    }

    @Test
    fun getMasterpieceVideoTest() = runTest {
        client.getMasterpieceVideo(client.getBasicInfo().data.mid!!)
    }

    @Test
    fun getUserTagsTest() = runTest {
        client.getUserTags(2)
    }

    @Test
    fun getSpaceAnnouncementTest() = runTest {
        client.getSpaceAnnouncement(2)
    }

    @Test
    fun getSpaceSettingTest() = runTest {
        client.getSpaceSetting(2).let {
            it.dataWhenFalse
            it.dataWhenTrue
        }
    }

    @Test
    fun getRecentPlayedGameTest() = runTest {
        client.getRecentPlayedGame(2)
    }

    @Test
    fun getRecentCoinedVideoTest() = runTest {
        client.getRecentCoinedVideo(client.getBasicInfo().data.mid!!)
    }

    @Test
    fun getSpaceVideoTest() = runTest {
        launch { client.getSpaceVideo(2) }
        launch { client.getSpaceVideo(2, type = Kichiku) }
        launch { client.getSpaceVideo(2, 2, 30, COLLECT) }
        launch { client.getSpaceVideo(2, keyword = "asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfas") }
    }

    @Test
    fun getSpaceAlbumIndexTest() = runTest {
        client.getSpaceAlbumIndex(63231, 20)
    }

    @Test
    fun getSpaceAlbumListTest() = runTest {
        client.getSpaceAlbumList(63231)
    }

    @Test
    fun getSpaceChannelTest() = runTest {
        client.getSpaceChannel(63231)
    }

    @Test
    fun getSpaceChannelArchivesTest() = runTest {
        client.getChannelArchives(63231, 139535)
    }

    @Test
    fun getCollectionListTest() = runTest {
        client.apply {
            getCollectionList(getBasicInfo().data.mid!!)
        }
    }

    @Test
    fun getFavCollectionListTest() = runTest {
        client.getFavCollectionList(63231)
    }
    
    @Test
    fun getMySpaceTest() {
        runTest {
            client.getMySpace()
        }
    }

    @Test
    fun nickCheckTest() {
        runTest {
            // lol 星黛*露b*ot 是敏感詞
            listOf("//", "test11145141919810123123123", "星黛露bot", "0", "1", "哈哈哈哈哈", "儑厫爊").forEach {
                client.checkNick(it)
            }
        }
    }
}
