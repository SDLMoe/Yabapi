package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.enums.video.Anime
import sdl.moe.yabapi.enums.video.Cinephile
import sdl.moe.yabapi.enums.video.Douga
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class RankingApiTest {

    init {
        initTest()
    }

    private val videoTypes = listOf(Douga, Douga.Other, Cinephile, Cinephile.Montage, Anime.Finish)

    @Test
    fun getRankingByTypeTest() {
        runTest {
            videoTypes.forEach {
                client.getRanking(it)
            }
        }
    }

    @Test
    fun getLatestVideoTest() {
        runTest {
            videoTypes.forEach {
                client.getLatestVideo(it)
            }
        }
    }
}
