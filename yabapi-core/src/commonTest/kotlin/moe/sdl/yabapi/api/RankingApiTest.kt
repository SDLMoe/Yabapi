package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.enums.video.Anime
import moe.sdl.yabapi.enums.video.Cinephile
import moe.sdl.yabapi.enums.video.Douga
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
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
