package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.enums.relation.FollowingOrder.MOST_FREQUENT
import moe.sdl.yabapi.enums.relation.RelationAction.ADD_BLACKLIST
import moe.sdl.yabapi.enums.relation.RelationAction.REMOVE_BLACKLIST
import moe.sdl.yabapi.enums.relation.RelationAction.REMOVE_FANS
import moe.sdl.yabapi.enums.relation.RelationAction.SUB
import moe.sdl.yabapi.enums.relation.RelationAction.SUB_QUIETLY
import moe.sdl.yabapi.enums.relation.RelationAction.UNSUB
import moe.sdl.yabapi.enums.relation.RelationAction.UNSUB_QUIETLY
import moe.sdl.yabapi.enums.relation.SubscribeSource.ACTIVITY
import moe.sdl.yabapi.enums.relation.SubscribeSource.VIDEO
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class RelationApiTest {
    init {
        initTest()
    }

    @Test
    fun getFansTest(): Unit = runTest {
        for (i in 1..5) {
            client.getFans(2, page = i)
        }
    }

    @Test
    fun getFollowingTest(): Unit = runTest {
        client.getFollowing(2)
        client.getFollowing(2, 1, 45, MOST_FREQUENT)
    }

    @Test
    fun searchFollowingTest(): Unit = runTest {
        val currentUserMid = client.getBasicInfo().data.mid
        requireNotNull(currentUserMid)
        listOf(2, 223902, currentUserMid).forEach {
            client.searchFollowing(it, "1")
        }
    }

    @Test
    fun getCoFollowingTest(): Unit = runTest {
        client.getCoFollowing(2)
    }

    @Test
    fun getQuietlyFollowingTest(): Unit = runTest {
        client.getQuietlyFollowing()
    }

    @Test
    fun getBlacklistTest(): Unit = runTest {
        client.getBlacklist()
    }

    @Test
    fun modifyTest(): Unit = runTest {
        client.modifyRelation(2, SUB)
        client.modifyRelation(2, UNSUB)
        client.modifyRelation(2, SUB_QUIETLY, VIDEO)
        client.modifyRelation(2, UNSUB_QUIETLY, ACTIVITY)
        client.modifyRelation(2, ADD_BLACKLIST)
        client.modifyRelation(2, REMOVE_BLACKLIST, VIDEO)
        client.modifyRelation(2, REMOVE_FANS)
    }

    @Test
    fun modifyBatchTest(): Unit = runTest {
        val testList = longArrayOf(1, 2, 3, 4, 5)
        client.modifyRelation(testList, SUB)
    }

    @Test
    fun queryRelationTest(): Unit = runTest {
        client.queryRelation(2)
        client.queryRelation(1, 2, 3, 4)
        client.queryRelationMutually(2)
        client.querySpecialFollowing()
    }
}
