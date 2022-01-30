package moe.sdl.yabapi.api

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.client
import moe.sdl.yabapi.data.search.LiveSearchOption
import moe.sdl.yabapi.data.search.SearchOption
import moe.sdl.yabapi.enums.search.SearchType
import moe.sdl.yabapi.enums.search.SearchType.LIVE
import moe.sdl.yabapi.enums.search.SearchType.LIVE_USER
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class SearchApiTest {
    init {
        initTest()
    }

    @Test
    fun getWebSearchDefaultTest() = runTest {
        listOf("原神", "我的世界", "拜年纪", "bilibili", "后浪", "影视飓风", "鲁迅说：“我没说过”", "腾讯", "英雄联盟").asFlow()
            .onEach { delay(2500) }
            .map { client.searchAll(it).data?.result?.value!! }
            .toList().flatten()
            .fold(mutableMapOf<String, MutableList<JsonObject>>()) { acc, result ->
                acc.getOrPut(result.resultType) {
                    mutableListOf()
                }.addAll(result.rawData)
                acc
            }.also {
                repeat(5) {
                    println("===========================================")
                }
                Json.encodeToString(it).also(::println)
            }
    }

    @Test
    fun getDeserializeTest() = runTest {
        listOf("原神", "我的世界", "拜年纪", "bilibili", "后浪", "影视飓风", "鲁迅说：“我没说过”", "腾讯", "英雄联盟").asFlow()
            .onEach { delay(2500) }
            .map { client.searchAll(it).data?.resultFlatted!! }
            .toList().flatten()
    }

    @Test
    fun searchByTypeTest() = runTest {
        SearchType.values().filter {
            it != LIVE && it != LIVE_USER
        }.forEach {
            client.searchByType("bilibili", SearchOption(type = it)).result
        }
    }

    @Test
    fun searchLiveTest() = runTest {
        client.searchLive("bilibili", LiveSearchOption()).data?.result.apply {
            this?.liveRoom.also(::println)
            this?.liveUser.also(::println)
        }
    }
}
