package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.PassportApi.loginWebConsole

fun main() = runBlocking {
    val client = BiliClient()
    client.loginWebConsole()
}
