// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.api.RelationApi.getBlacklist
import sdl.moe.yabapi.api.RelationApi.getCoFollowing
import sdl.moe.yabapi.api.RelationApi.getFans
import sdl.moe.yabapi.api.RelationApi.getFollowing
import sdl.moe.yabapi.api.RelationApi.getQuietlyFollowing
import sdl.moe.yabapi.api.RelationApi.modifyRelation
import sdl.moe.yabapi.api.RelationApi.queryRelation
import sdl.moe.yabapi.api.RelationApi.queryRelationMutually
import sdl.moe.yabapi.api.RelationApi.querySpecialFollowing
import sdl.moe.yabapi.api.RelationApi.searchFollowing
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.enums.relation.FollowingOrder.MOST_FREQUENT
import sdl.moe.yabapi.enums.relation.RelationAction.ADD_BLACKLIST
import sdl.moe.yabapi.enums.relation.RelationAction.REMOVE_BLACKLIST
import sdl.moe.yabapi.enums.relation.RelationAction.REMOVE_FANS
import sdl.moe.yabapi.enums.relation.RelationAction.SUB
import sdl.moe.yabapi.enums.relation.RelationAction.SUB_QUIETLY
import sdl.moe.yabapi.enums.relation.RelationAction.UNSUB
import sdl.moe.yabapi.enums.relation.RelationAction.UNSUB_QUIETLY
import sdl.moe.yabapi.enums.relation.SubscribeSource.ACTIVITY
import sdl.moe.yabapi.enums.relation.SubscribeSource.VIDEO
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.test.Test

internal class RelationApiTest {
    init {
        yabapiLogLevel = DEBUG
    }

    private val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

    @Test
    fun getFansTest() {
        runBlocking {
            for (i in 1..5) {
                client.getFans(2, page = i)
            }
        }
    }

    @Test
    fun getFollowingTest() {
        runBlocking {
            client.getFollowing(2)
            client.getFollowing(2, 1, 45, MOST_FREQUENT)
        }
    }

    @Test
    fun searchFollowingTest() {
        runBlocking {
            val currentUserMid = client.getBasicInfo().data.mid
            requireNotNull(currentUserMid)
            listOf(2, 223902, currentUserMid).forEach {
                client.searchFollowing(it, "1")
            }
        }
    }

    @Test
    fun getCoFollowingTest() {
        runBlocking {
            client.getCoFollowing(2)
        }
    }

    @Test
    fun getQuietlyFollowingTest() {
        runBlocking {
            client.getQuietlyFollowing()
        }
    }

    @Test
    fun getBlacklistTest() {
        runBlocking {
            client.getBlacklist()
        }
    }

    @Test
    fun modifyTest() {
        runBlocking {
            client.modifyRelation(2, SUB)
            client.modifyRelation(2, UNSUB)
            client.modifyRelation(2, SUB_QUIETLY, VIDEO)
            client.modifyRelation(2, UNSUB_QUIETLY, ACTIVITY)
            client.modifyRelation(2, ADD_BLACKLIST)
            client.modifyRelation(2, REMOVE_BLACKLIST, VIDEO)
            client.modifyRelation(2, REMOVE_FANS)
        }
    }

    @Test
    fun modifyBatchTest() {
        runBlocking {
            val testList = listOf(1, 2, 3, 4, 5)
            client.modifyRelation(testList, SUB)
            client.modifyRelation(testList, UNSUB)
        }
    }

    @Test
    fun queryRelationTest() {
        runBlocking {
            client.queryRelation(2)
            client.queryRelation(1, 2, 3, 4)
            client.queryRelationMutually(2)
            client.querySpecialFollowing()
        }
    }

    @Test
    fun testAll() {
        getFansTest()
        getFollowingTest()
        searchFollowingTest()
        getCoFollowingTest()
        getQuietlyFollowingTest()
        getBlacklistTest()
        modifyTest()
        modifyBatchTest()
        queryRelationTest()
    }
}
