package moe.sdl.yabapi.api

import kotlinx.coroutines.launch
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
    fun getBasicInfoTest(): Unit = runTest {
        // client.loginWebQRCodeInteractive()
        client.getBasicInfo()
    }

    @Test
    fun getStatTest(): Unit = runTest {
        client.getStat()
    }

    @Test
    fun getCoinTest(): Unit = runTest {
        client.getCoinInfo()
    }

    @Test
    fun getAccountInfoTest(): Unit = runTest {
        client.getAccountInfo()
    }

    @Test
    fun getExpTest(): Unit = runTest {
        val expData = client.getExpReward().data
        val coinExpData = client.getCoinExp()
        expData?.replaceWithCoinExp(coinExpData)?.countTodayRewarded().also {
            logger.info { "Sum: $it" }
        }
    }

    @Test
    fun getVipStatTest(): Unit = runTest {
        client.getVipStat()
    }

    @Test
    fun getSecureInfoTest(): Unit = runTest {
        client.getSecureInfo()
    }

    @Test
    fun getRealNameInfoTest(): Unit = runTest {
        client.getRealNameInfo()
    }

    @Test
    fun getRealNameDetailedTest(): Unit = runTest {
        client.getRealNameDetailed()
    }

    @Test
    fun getCoinLogTest(): Unit = runTest {
        client.getCoinLog()
    }

    @Test
    fun getUserSpaceTest(): Unit = runTest {
        longArrayOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
            .forEach {
                client.getUserSpace(it)
            }
    }

    @Test
    fun getUserCardTest(): Unit = runTest {
        val random = listOf(true, false)
        // val body: String = client.client.get(USER_CARD_GET_URL) {
        //     parameter("mid", "2")
        //     parameter("photo", "true")
        // }
        // println(body)
        longArrayOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
            .forEach {
                client.getUserCard(it, random.random())
            }
    }

    @Test
    fun getPinnedVideoTest(): Unit = runTest {
        client.getPinnedVideo(client.getBasicInfo().data.mid!!)
    }

    @Test
    fun getMasterpieceVideoTest(): Unit = runTest {
        client.getMasterpieceVideo(client.getBasicInfo().data.mid!!)
    }

    @Test
    fun getUserTagsTest(): Unit = runTest {
        client.getUserTags(2)
    }

    @Test
    fun getSpaceAnnouncementTest(): Unit = runTest {
        client.getSpaceAnnouncement(2)
    }

    @Test
    fun getSpaceSettingTest(): Unit = runTest {
        client.getSpaceSetting(2).let {
            it.dataWhenFalse
            it.dataWhenTrue
        }
    }

    @Test
    fun getRecentPlayedGameTest(): Unit = runTest {
        client.getRecentPlayedGame(2)
    }

    @Test
    fun getRecentCoinedVideoTest(): Unit = runTest {
        client.getRecentCoinedVideo(client.getBasicInfo().data.mid!!)
    }

    @Test
    fun getSpaceVideoTest(): Unit = runTest {
        launch { client.getSpaceVideo(2) }
        launch { client.getSpaceVideo(2, type = Kichiku) }
        launch { client.getSpaceVideo(2, 2, 30, COLLECT) }
        launch { client.getSpaceVideo(2, keyword = "asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfas") }
    }

    @Test
    fun getSpaceAlbumIndexTest(): Unit = runTest {
        client.getSpaceAlbumIndex(63231, 20)
    }

    @Test
    fun getSpaceAlbumListTest(): Unit = runTest {
        client.getSpaceAlbumList(63231)
    }

    @Test
    fun getSpaceChannelTest(): Unit = runTest {
        client.getSpaceChannel(63231)
    }

    @Test
    fun getSpaceChannelArchivesTest(): Unit = runTest {
        client.getChannelArchives(63231, 139535)
    }

    @Test
    fun getFavoritesListTest(): Unit = runTest {
        client.apply {
            getFavorites(getBasicInfo().data.mid!!)
        }
    }

    @Test
    fun getCollectedFavoritesListTest(): Unit = runTest {
        client.getCollectedFavorites(63231)
    }

    @Test
    fun getSubscribedBangumiTest(): Unit = runTest {
        client.getSubscribedBangumi(63231)
    }

    @Test
    fun getSubscribedTagsTest(): Unit = runTest {
        client.apply {
            getSubscribedTags(getBasicInfo().data.mid!!).also {
                println(it.data)
                println(it.failedMsg)
            }
        }
    }

    @Test
    fun getMySpaceTest(): Unit = runTest {
        client.getMySpace()
    }

    @Test
    fun nickCheckTest(): Unit = runTest {
        // lol 星黛*露b*ot 是敏感詞
        listOf("//", "test11145141919810123123123", "星黛露bot", "0", "1", "哈哈哈哈哈", "儑厫爊").forEach {
            client.checkNick(it)
        }
    }

    @Test
    fun getFavoritesInfoTest(): Unit = runTest {
        client.getFavoritesInfo(1052622027)
    }

    @Test
    fun getFavoritesMediaTest(): Unit = runTest {
        client.getFavoritesMedia(1052622027)
    }

    @Test
    fun getFavoritesTypesTest(): Unit = runTest {
        client.getFavoritesTypes(25554216, 83867716)
    }
}
