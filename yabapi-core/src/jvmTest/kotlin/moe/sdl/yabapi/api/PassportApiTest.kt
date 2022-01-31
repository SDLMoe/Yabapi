package moe.sdl.yabapi.api

import kotlinx.coroutines.runBlocking
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.initTest

fun main() = loginPwdTest()

fun loginSmsTest(): Unit = runBlocking {
    initTest()
    BiliClient().loginWebSMSConsole(true)
}

fun loginPwdTest(): Unit = runBlocking {
    initTest()
    BiliClient().loginWebConsole()
}
