package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
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
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class RelationApiTest {
    init {
        initTest()
    }

    @Test
    fun getFansTest() {
        runTest {
            for (i in 1..5) {
                client.getFans(2, page = i)
            }
        }
    }

    @Test
    fun getFollowingTest() {
        runTest {
            client.getFollowing(2)
            client.getFollowing(2, 1, 45, MOST_FREQUENT)
        }
    }

    @Test
    fun searchFollowingTest() {
        runTest {
            val currentUserMid = client.getBasicInfo().data.mid
            requireNotNull(currentUserMid)
            listOf(2, 223902, currentUserMid).forEach {
                client.searchFollowing(it, "1")
            }
        }
    }

    @Test
    fun getCoFollowingTest() {
        runTest {
            client.getCoFollowing(2)
        }
    }

    @Test
    fun getQuietlyFollowingTest() {
        runTest {
            client.getQuietlyFollowing()
        }
    }

    @Test
    fun getBlacklistTest() {
        runTest {
            client.getBlacklist()
        }
    }

    @Test
    fun modifyTest() {
        runTest {
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
        runTest {
            val testList = listOf(1, 2, 3, 4, 5)
            client.modifyRelation(testList, SUB)
            client.modifyRelation(testList, UNSUB)
        }
    }

    @Test
    fun queryRelationTest() {
        runTest {
            client.queryRelation(2)
            client.queryRelation(1, 2, 3, 4)
            client.queryRelationMutually(2)
            client.querySpecialFollowing()
        }
    }
}
